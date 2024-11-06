package dev.abhishekprakash.starterkit.Events;

import dev.abhishekprakash.starterkit.Entities.BlogIndex;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class BlogsGeneratedEvent extends ApplicationEvent {

    private final List<BlogIndex> blogIndices;

    public BlogsGeneratedEvent(List<BlogIndex> blogIndices) {
        super(blogIndices);
        this.blogIndices = blogIndices;
    }

}