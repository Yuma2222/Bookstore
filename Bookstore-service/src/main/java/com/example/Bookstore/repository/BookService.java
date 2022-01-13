package com.example.Bookstore.repository;

import com.example.Bookstore.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        //Need to ask about better solution.
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getAuthor() != null && book.getAuthor().equals(filters.get("author")))
                .filter(book -> book.getGenre() != null && book.getGenre().equals(filters.get("genre")))
                .filter(book -> book.getPublisher() != null && book.getPublisher().equals(filters.get("publisher")))
                .collect(Collectors.toList());
    }

    public int addBook(Book book) {
        return bookRepository.save(book).getId();
    }

    public int updateBook(Book book){
        //Need to ask about better solution.
        Book bookToUpdate = bookRepository.getById(book.getId());
        bookToUpdate.setNumberOfPages(book.getNumberOfPages());
        bookToUpdate.setReleaseDate(book.getReleaseDate());
        bookToUpdate.setReleaseLanguage(book.getReleaseLanguage());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setGenre(book.getGenre());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setPublisher(book.getPublisher());
        bookToUpdate.setISBN(book.getISBN());

        return bookRepository.save(bookToUpdate).getId();
    }

    public int removeBook(int id) {
        bookRepository.deleteById(id);
        return id;
    }
}
