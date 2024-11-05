package dev.abhishekprakash.personalwebsite.Core.Renderer;

import dev.abhishekprakash.personalwebsite.Contracts.Renderable;
import dev.abhishekprakash.personalwebsite.Core.FileSystem.File;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.Renderer;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.file.Files;

public class MarkdownToHtmlRenderer implements Renderable<RenderedDocument> {

    File markdown;

    Parser markdownParser = Parser.builder().build();

    Renderer htmlRenderer = HtmlRenderer.builder()
            .attributeProviderFactory(context -> new HtmlAttributeProvider())
            .build();

    public MarkdownToHtmlRenderer(File markdown) {
        this.markdown = markdown;
    }

    public RenderedDocument render() throws IOException {
        String markdownContent = Files.readString(markdown.toPath());

        Node document = markdownParser.parse(markdownContent);

        String documentTitle = getDocumentTitle(document.getFirstChild());
        String documentContent = htmlRenderer.render(document);

        return new RenderedDocument(documentTitle, documentContent, markdown);
    }

    private String getDocumentTitle(Node node) {
        Node temp = node;

        while (temp != null) {
            if (temp instanceof Heading) {
                return ((Text) node.getFirstChild()).getLiteral();
            }

            temp = temp.getNext();
        }

        return "";
    }

}