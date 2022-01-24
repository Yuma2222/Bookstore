package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GoogleApiBookDTO {
    private String author;
    private String title;
    private String genre;
    private String description;
    private String publisher;
    private String ISBN;
    private String releaseLanguage;
    private int numberOfPages;
    private int releaseNumber;
}
