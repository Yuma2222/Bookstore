package com.example.Bookstore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private int id;
    private String author;
    @Column(unique = true)
    private String title;
    private String genre;
    private String description;
    private String publisher;
    @Column(unique = true)
    private String ISBN;
    private String releaseLanguage;
    private int numberOfPages;
    private int releaseNumber;
}
