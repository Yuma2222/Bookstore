package com.example.Bookstore.servicesTest;

import com.example.Bookstore.repository.BookRepository;
import com.example.Bookstore.repository.BookService;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    BookService bookService = new BookService(bookRepository);

}
