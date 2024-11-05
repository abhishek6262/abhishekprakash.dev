package dev.abhishekprakash.personalwebsite.Events;

import dev.abhishekprakash.personalwebsite.Entities.BlogIndex;
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