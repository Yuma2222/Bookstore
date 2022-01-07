package com.example.Bookstore.cotrollers;

import com.example.Bookstore.models.Book;
import com.example.Bookstore.repository.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/Books")
    public ResponseEntity<List<Book>> showBooks(){
        return new ResponseEntity<>(bookService.findBooks(), HttpStatus.OK);
    }

    @GetMapping("/Books/byTitle/{fragment}")
    public ResponseEntity<List<Book>> showBooksByTitleFragment(@PathVariable String fragment){
        return new ResponseEntity<>(bookService.findBooksByTitleFragment(fragment), HttpStatus.OK);
    }

    @GetMapping("/Books/byFilters")
    public ResponseEntity<List<Book>> showBooksByParams(@RequestParam Map<String, String> filters){
        return new ResponseEntity<>(bookService.findBooksByFilter(filters), HttpStatus.OK);
    }

    @PostMapping("/Books/add")
    public ResponseEntity<Integer> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
    }

    @PutMapping("/Books/update")
    public ResponseEntity<Integer> updateBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
    }

    @DeleteMapping("/Books/delete/{id}")
    public ResponseEntity<Integer> removeBookById(@PathVariable int id){
        return new ResponseEntity<>(bookService.removeBook(id), HttpStatus.OK);
    }
}
