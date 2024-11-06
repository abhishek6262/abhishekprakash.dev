package dev.abhishekprakash.starterkit.Core.Renderer;

import dev.abhishekprakash.starterkit.Core.FileSystem.File;
import lombok.Getter;

@Getter
public class RenderedDocument {

    private final String title;

    private final String content;

    private final File source;

    RenderedDocument(String title, String content, File source) {
        this.title = title;
        this.content = content;
        this.source = source;
    }

}