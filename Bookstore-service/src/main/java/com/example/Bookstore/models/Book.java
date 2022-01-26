package com.example.Bookstore.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private int id;
    @ElementCollection
    private List<String> authors;
    @ElementCollection
    private List<String> categories;
    private String title;
    @Lob
    @Column(length = 1024)
    private String description;
    private String publisher;
    private String contentVersion;
    private String language;
    private int pageCount;
}
