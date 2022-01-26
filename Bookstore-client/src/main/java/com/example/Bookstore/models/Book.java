package com.example.Bookstore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private int id;
    @ElementCollection
    private List<String> authors;
    @ElementCollection
    private List<String> categories;
    private String title;
    private String description;
    private String publisher;
    private String contentVersion;
    private String language;
    private int pageCount;
}
