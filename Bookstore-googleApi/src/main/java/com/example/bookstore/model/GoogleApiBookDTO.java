package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleApiBookDTO {
    private List<String> authors;
    private List<String> categories;
    private String title;
    private String description;
    private String publisher;
    private String contentVersion;
    private String language;
    private int pageCount;
}
