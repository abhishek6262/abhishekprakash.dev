package dev.abhishekprakash.starterkit.Services;

import dev.abhishekprakash.starterkit.Entities.BlogIndex;
import dev.abhishekprakash.starterkit.Repositories.BlogIndicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogIndicesRepository blogIndicesRepository;

    public List<BlogIndex> getBlogIndices() {
        return blogIndicesRepository.getBlogIndices();
    }

    public Optional<BlogIndex> getBlogIndexBySlug(String slug) {
        return blogIndicesRepository.getBlogIndexBySlug(slug);
    }

}