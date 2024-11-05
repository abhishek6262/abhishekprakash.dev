package dev.abhishekprakash.personalwebsite.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class AppConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public Path tempDirectoryPath() {
        String systemTempDirectory = System.getProperty("java.io.tmpdir");

        return Path.of(systemTempDirectory, appName);
    }

}