package com.example.bookstore.service;

import com.example.bookstore.model.GoogleApiBookDTO;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class GoogleApiBookService {
    public List<GoogleApiBookDTO> getAllBooks() {
        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=flowers&orderBy=newest&key=");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
