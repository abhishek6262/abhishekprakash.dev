package dev.abhishekprakash.personalwebsite.Hooks;

import dev.abhishekprakash.personalwebsite.Actions.GenerateBlogAction;
import dev.abhishekprakash.personalwebsite.Core.FileSystem.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class ApplicationStartupHook implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private GenerateBlogAction generateBlogAction;

    @Autowired
    private Path tempDirectoryPath;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            FileSystem.deleteDirectoryRecursiveIfExists(tempDirectoryPath);

            // This directory will be used to store the app cache
            // such as compiled markdowns and blogs list.
            FileSystem.createDirectoryIfNotExists(tempDirectoryPath);

            generateBlogAction.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}