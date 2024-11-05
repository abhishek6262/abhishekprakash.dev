package dev.abhishekprakash.personalwebsite.Core.FileSystem;

import java.net.URI;

public class File extends java.io.File {

    public File(String pathname) {
        super(pathname);
    }

    public File(String parent, String child) {
        super(parent, child);
    }

    public File(java.io.File parent, String child) {
        super(parent, child);
    }

    public File(URI uri) {
        super(uri);
    }

    public String getBasename() {
        String name = this.getName();
        return name.substring(0, name.lastIndexOf('.'));
    }
    
}