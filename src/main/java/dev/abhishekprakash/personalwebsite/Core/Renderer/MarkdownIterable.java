package dev.abhishekprakash.personalwebsite.Core.Renderer;

import dev.abhishekprakash.personalwebsite.Core.FileSystem.File;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class MarkdownIterable implements Iterable<File> {

    private final Path markdownDir;

    public MarkdownIterable(Path markdownDir) {
        this.markdownDir = markdownDir;
    }

    @Override
    public Iterator<File> iterator() {
        return new MarkdownIterator();
    }

    class MarkdownIterator implements Iterator<File> {

        private int prevIndex = -1;

        private final File[] markdowns = Arrays.stream(markdownDir.toFile().listFiles())
                .map((file) -> new File(file.toURI()))
                .toArray(File[]::new);

        @Override
        public boolean hasNext() {
            for (int i = prevIndex + 1; i < Objects.requireNonNull(markdowns).length; i++) {
                File file = markdowns[i];

                if (file.isFile() && file.getName().endsWith(".md")) {
                    prevIndex = i;
                    return true;
                }
            }

            return false;
        }

        @Override
        public File next() {
            return markdowns[prevIndex];
        }

    }

}