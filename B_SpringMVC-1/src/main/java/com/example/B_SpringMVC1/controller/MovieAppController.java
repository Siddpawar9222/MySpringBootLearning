package com.example.B_SpringMVC1.controller;

import com.example.B_SpringMVC1.config.ApiConfig;
import com.example.B_SpringMVC1.model.Movie;
import com.example.B_SpringMVC1.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieAppController {

     private final MovieService movieService;
     private final ApiConfig apiConfig;

     @GetMapping("/movies")
     public String movieApp(Model model) throws IOException {
          List<Movie> movieList = movieService.fetchData(apiConfig.getMovies());
          model.addAttribute("movieList", movieList);
          return "/WEB-INF/movies.jsp";
     }
}
