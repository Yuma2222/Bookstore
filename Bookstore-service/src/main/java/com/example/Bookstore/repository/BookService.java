package com.example.Bookstore.repository;

import com.example.Bookstore.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    public List<Book> findBooksByTitleFragment(String titleFragment) {
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getTitle().contains(titleFragment))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByFilter(Map<String, String> filters) {
    /*    List<Book> all = bookRepository.findAll();

        List<Predicate<Book>> allPredicates = Arrays.asList(
                w -> w.getAuthor().equals(filters.get("author")),
                w -> w.getGenre().equals(filters.get("genre")),
                w -> w.getPublisher().equals(filters.get("publisher")));

        Predicate<Book> compositePredicate = allPredicates.stream().reduce(w -> true, Predicate::and);

        all.stream().filter(compositePredicate).forEach(System.out::println);*/

      /*  return bookRepository.findAll()
                .stream()
                .filter(book -> book.getAuthor() != null && book.getAuthor().equals(filters.get("author")))
                .filter(book -> book.getGenre() != null && book.getGenre().equals(filters.get("genre")))
                .filter(book -> book.getPublisher() != null && book.getPublisher().equals(filters.get("publisher")))
                .collect(Collectors.toList());*/
        return null;
    }

    public int addBook(Book book) {
        return bookRepository.save(book).getId();
    }

    public int updateBook(Book book) {
        return bookRepository.save(book).getId();
    }

    public int removeBook(int id) {
        bookRepository.deleteById(id);
        return id;
    }

    public String reloadBookFromGoogleApi(){
        String API_URL = "http://localhost:8091";
        WebClient WEB_CLIENT = WebClient.create(API_URL);
        Book[] books = WEB_CLIENT.get()
                .uri("/googleApi/getAll")
                .retrieve()
                .bodyToMono(Book[].class).block();

        assert books != null;
        bookRepository.saveAll(Arrays.asList(books));

        return "Books are up to date";
    }
}
