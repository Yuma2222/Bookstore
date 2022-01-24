package com.example.Bookstore.controllers;

import com.example.Bookstore.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {
    private static final String API_URL = "http://localhost:8085";
    private static final WebClient WEB_CLIENT = WebClient.create(API_URL);

    @GetMapping("/books/display")
    public String displayCars(Model model) {
        Book[] books = WEB_CLIENT.get()
                .uri("/books/display")
                .retrieve()
                .bodyToMono(Book[].class).block();

        assert books != null;
        List<Book> bookList = Arrays.asList(books);
        model.addAttribute("books", bookList);

        return "index";
    }

    @GetMapping("/books/add")
    public String displayCreateCarForm(Model model) {
        model.addAttribute("book", new Book());

        return "addForm";
    }

    @PostMapping("/books/add")
    public String submitCreateCarForm(@ModelAttribute Book book) {
        Mono<Long> bookId = WEB_CLIENT.post()
                .uri("/books/add")
                .body(Mono.just(book), Book.class)
                .retrieve()
                .bodyToMono(Long.class);
        bookId.subscribe();

        return "redirect:/books/display";
    }

}