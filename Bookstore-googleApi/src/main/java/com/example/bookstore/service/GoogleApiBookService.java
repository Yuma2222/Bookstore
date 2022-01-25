package com.example.bookstore.service;

import com.example.bookstore.model.GoogleApiBookDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Service
public class GoogleApiBookService {

    public List<GoogleApiBookDTO> getAllBooks() {
        try {

            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=flowers&orderBy=newest&key="+System.getenv("googleBookApi"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
            } else {
                StringBuilder jsonInLine = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    jsonInLine.append(scanner.nextLine());
                }
                scanner.close();
                JSONObject response = new JSONObject(jsonInLine.toString());
                JSONArray allItems = response.getJSONArray("items");
                JSONObject volumeInfo = allItems.getJSONObject(0).getJSONObject("volumeInfo");
                ObjectMapper mapper = new ObjectMapper();
                GoogleApiBookDTO googleApiBookDTO = mapper.readValue(volumeInfo.toString(), GoogleApiBookDTO.class);
                System.out.println(googleApiBookDTO.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void connectToGoogleApi(){

    }
}
