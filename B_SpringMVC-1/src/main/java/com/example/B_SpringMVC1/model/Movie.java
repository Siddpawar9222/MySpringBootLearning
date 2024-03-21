package com.example.B_SpringMVC1.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Movie {
    private int id;
    private String movie;
    private double rating;
    private String image;
    private String imdb_url;
}
/*
This field name must be equal to response field . Otherwise error
*/