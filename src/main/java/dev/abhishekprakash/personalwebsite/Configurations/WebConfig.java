package dev.abhishekprakash.personalwebsite.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void configurePathMatch(PathMatchConfigurer configurer) {
        // TODO: `setUseTrailingSlashMatch` is deprecated. Look for alternatives.
        configurer.setUseTrailingSlashMatch(true);
    }

}