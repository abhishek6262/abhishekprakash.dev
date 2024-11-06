package dev.abhishekprakash.starterkit.Configurations;

import dev.abhishekprakash.starterkit.Core.FileSystem.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Configuration
public class BlogsConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private Path tempDirectoryPath;

    @Bean
    public Path blogsHtmlDirectoryPath() {
        return tempDirectoryPath.resolve("articles");
    }

    @Bean
    public Path blogsMarkdownDirectoryPath() throws IOException {
        // We need to create a temporary markdowns copy because once
        // the application is packaged in production build then we can't
        // directly access the markdowns from the jar file without converting
        // the files into a stream.
        return createMarkdownsTempCopy();
    }

    private Path createMarkdownsTempCopy() throws IOException {
        Path markdownsDir = FileSystem.createTempDirectory("markdowns");

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(resourceLoader);
        Resource[] resources = resolver.getResources("classpath:/articles/*");

        for (Resource resource : resources) {
            String fileName = resource.getFilename();

            if (fileName != null) {
                Path tempFile = markdownsDir.resolve(fileName);

                try (InputStream inputStream = resource.getInputStream()) {
                    Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }

        return markdownsDir;
    }
}