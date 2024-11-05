package dev.abhishekprakash.personalwebsite.Controllers;

import dev.abhishekprakash.personalwebsite.Entities.BlogIndex;
import dev.abhishekprakash.personalwebsite.Services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String getBlogs(Model model) {
        List<BlogIndex> blogIndices = blogService.getBlogIndices();

        model.addAttribute("blogIndices", blogIndices);

        return "blogs";
    }

    @GetMapping("{slug}")
    public String getBlog(@PathVariable("slug") String slug, Model model) {
        BlogIndex blogIndex = blogService
                .getBlogIndexBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("blogTitle", blogIndex.getTitle());
        model.addAttribute("blogHtmlTemplatePath", "articles/" + blogIndex.getSlug());

        return "blog";
    }

}