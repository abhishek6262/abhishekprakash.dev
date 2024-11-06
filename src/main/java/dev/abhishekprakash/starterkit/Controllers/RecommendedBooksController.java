package dev.abhishekprakash.starterkit.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recommended-books")
public class RecommendedBooksController {

    @GetMapping
    public String getRecommendedBooks() {
        return "recommended-books";
    }

}