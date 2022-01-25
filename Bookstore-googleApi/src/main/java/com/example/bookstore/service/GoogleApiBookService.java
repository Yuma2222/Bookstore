package com.example.bookstore.service;

import com.example.bookstore.model.GoogleApiBookDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class GoogleApiBookService {

    private static final String GOOGLE_API_URL = "https://www.googleapis.com/books/v1/volumes?q=flowers&maxResults=40&key=" + System.getenv("googleBookApi");

    private URL connToGoogleApi() {
        URL url = null;

        try {
            url = new URL(GOOGLE_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Google HttpResponseCode: " + conn.getResponseCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public List<GoogleApiBookDTO> getAllBooks() {
        List<GoogleApiBookDTO> googleBooks = new ArrayList<>();
        StringBuilder jsonInLine = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();

        try {
            Scanner scanner = new Scanner(connToGoogleApi().openStream());
            while (scanner.hasNext()) {
                jsonInLine.append(scanner.nextLine());
            }
            scanner.close();

            JSONArray allItems = new JSONObject(jsonInLine.toString()).getJSONArray("items");
            GoogleApiBookDTO googleApiBookDTO;
            JSONObject bookInfo;

            for (int i = 0; i < allItems.length(); i++) {
                bookInfo = allItems.getJSONObject(i).getJSONObject("volumeInfo");
                googleApiBookDTO = mapper.readValue(bookInfo.toString(), GoogleApiBookDTO.class);
                googleBooks.add(googleApiBookDTO);
            }
            System.out.println(allItems.length());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return googleBooks;
    }
}
