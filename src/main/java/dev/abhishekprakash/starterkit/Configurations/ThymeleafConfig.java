package dev.abhishekprakash.starterkit.Configurations;

import dev.abhishekprakash.starterkit.Core.FileSystem.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.nio.file.Path;

@Configuration
public class ThymeleafConfig {

    @Autowired
    private Path tempDirectoryPath;

    @Autowired
    private SpringResourceTemplateResolver defaultTemplateResolver;

    @Bean
    public FileTemplateResolver tempTemplateResolver() {
        FileTemplateResolver templateResolver = new FileTemplateResolver();

        templateResolver.setPrefix(tempDirectoryPath.toString() + File.separator);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCheckExistence(true);
        templateResolver.setOrder(0);  // Higher priority than the default resolver

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(FileTemplateResolver tempTemplateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        templateEngine.addTemplateResolver(tempTemplateResolver);
        templateEngine.addTemplateResolver(defaultTemplateResolver);

        return templateEngine;
    }
}