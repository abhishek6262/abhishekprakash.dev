package dev.abhishekprakash.starterkit.Core.FileSystem;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystem {

    public static void createDirectoryIfNotExists(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
    }

    public static void deleteDirectoryRecursiveIfExists(Path dir) throws IOException {
        FileUtils.deleteDirectory(dir.toFile());
    }

}