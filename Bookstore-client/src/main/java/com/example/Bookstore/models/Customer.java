package com.example.Bookstore.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    private int age;
    private String name;
    private String surname;
    private String eMail;
    @OneToMany @Nullable
    private List<Book> bookList;
}
