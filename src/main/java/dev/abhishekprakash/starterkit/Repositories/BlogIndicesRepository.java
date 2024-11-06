package dev.abhishekprakash.starterkit.Repositories;

import dev.abhishekprakash.starterkit.Entities.BlogIndex;
import dev.abhishekprakash.starterkit.Events.BlogsGeneratedEvent;
import lombok.Getter;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Repository
public class BlogIndicesRepository {

    private List<BlogIndex> blogIndices = new ArrayList<>();

    @EventListener(BlogsGeneratedEvent.class)
    public void onBlogsGeneratedEvent(BlogsGeneratedEvent event) {
        blogIndices = event.getBlogIndices();
    }

    public Optional<BlogIndex> getBlogIndexBySlug(String slug) {
        return blogIndices.stream()
                .filter(blogIndex -> blogIndex.getSlug().equals(slug))
                .findFirst();
    }

}