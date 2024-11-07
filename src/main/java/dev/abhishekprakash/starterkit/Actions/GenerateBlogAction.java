package dev.abhishekprakash.starterkit.Actions;

import dev.abhishekprakash.starterkit.Contracts.Actionable;
import dev.abhishekprakash.starterkit.Core.FileSystem.File;
import dev.abhishekprakash.starterkit.Core.FileSystem.FileSystem;
import dev.abhishekprakash.starterkit.Core.Renderer.MarkdownIterable;
import dev.abhishekprakash.starterkit.Core.Renderer.MarkdownToHtmlRenderer;
import dev.abhishekprakash.starterkit.Core.Renderer.RenderedDocument;
import dev.abhishekprakash.starterkit.Core.Support.Tap;
import dev.abhishekprakash.starterkit.Entities.BlogIndex;
import dev.abhishekprakash.starterkit.Events.BlogsGeneratedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class GenerateBlogAction implements Actionable {

    @Autowired
    private Path blogsHtmlDirectoryPath;

    @Autowired
    private Path blogsMarkdownDirectoryPath;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void execute() throws Exception {
        new Tap<>(renderMarkdownAsHtml())
                .through(this::storeRenderedDocumentAsHtml)
                .through(this::constructIndices)
                .execute();
    }

    private List<RenderedDocument> renderMarkdownAsHtml() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<RenderedDocument>> renderedDocumentsCallables = new ArrayList<>();

        for (File markdown : new MarkdownIterable(blogsMarkdownDirectoryPath)) {
            renderedDocumentsCallables.add(() -> new MarkdownToHtmlRenderer(markdown).render());
        }

        List<Future<RenderedDocument>> renderedDocumentsFutures = executorService.invokeAll(renderedDocumentsCallables);

        return renderedDocumentsFutures.stream().map(renderedDocumentFuture -> {
            try {
                return renderedDocumentFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    private void storeRenderedDocumentAsHtml(List<RenderedDocument> renderedDocuments) throws IOException {
        FileSystem.createDirectoryIfNotExists(blogsHtmlDirectoryPath);

        for (RenderedDocument renderedDocument : renderedDocuments) {
            File source = renderedDocument.getSource();
            String fileName = source.getBasename() + ".html";

            Path filePath = blogsHtmlDirectoryPath.resolve(fileName);

            Files.writeString(filePath, renderedDocument.getContent());
        }
    }

    private void constructIndices(List<RenderedDocument> renderedDocuments) {
        List<BlogIndex> indices = castRenderedDocumentsToArticles(renderedDocuments);
        BlogsGeneratedEvent blogsGeneratedEvent = new BlogsGeneratedEvent(indices);

        applicationEventPublisher.publishEvent(blogsGeneratedEvent);
    }

    private List<BlogIndex> castRenderedDocumentsToArticles(List<RenderedDocument> renderedDocuments) {
        return renderedDocuments
                .stream()
                .map(renderedDocument -> {
                    String title = renderedDocument.getTitle();
                    String slug = renderedDocument.getSource().getBasename();

                    return new BlogIndex(title, slug);
                })
                .toList();
    }

}