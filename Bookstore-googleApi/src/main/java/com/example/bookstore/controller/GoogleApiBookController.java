package com.example.bookstore.controller;

import com.example.bookstore.model.GoogleApiBookDTO;
import com.example.bookstore.service.GoogleApiBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoogleApiBookController {

    GoogleApiBookService googleApiBookService;

    @Autowired
    public GoogleApiBookController(GoogleApiBookService googleApiBookService) {
        this.googleApiBookService = googleApiBookService;
    }

    @GetMapping("/get/all")
    public List<GoogleApiBookDTO> getBooksFromGoogleApi() {
        return googleApiBookService.getAllBooks();
    }
}
