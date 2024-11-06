package dev.abhishekprakash.starterkit.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Path;

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
        return resourceLoader
                .getResource("classpath:articles")
                .getFile()
                .toPath();
    }

}