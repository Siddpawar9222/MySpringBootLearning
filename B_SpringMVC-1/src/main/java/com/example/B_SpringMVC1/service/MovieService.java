package com.example.B_SpringMVC1.service;


import com.example.B_SpringMVC1.config.ApiConfig;
import com.example.B_SpringMVC1.model.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final ApiConfig apiConfig ;
    public List<Movie> fetchData(String serchEndpoint) throws IOException {
        System.out.println(apiConfig.getUrl()+serchEndpoint);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()

                .url(apiConfig.getUrl()+serchEndpoint)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String jsonData = responseBody.string();

                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<List<Movie>> typeReference = new TypeReference<>() {};
                List<Movie> movieList = objectMapper.readValue(jsonData, typeReference);

                responseBody.close();
                System.out.println(movieList);
                return movieList;
            } else {
                System.out.println("Response body is empty.");
            }
        } else {
            System.out.println("Request failed. Response code: " + response.code());
        }

        return Collections.emptyList();

    }
}
/*
Convert response data into List
-ObjectMapper :  helps you convert between JSON data and Java objects.
-TypeReference (Abstract class) : specifying the target type you want to convert the JSON data into.
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
 */