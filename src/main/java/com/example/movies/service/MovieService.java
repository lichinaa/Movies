package com.example.movies.service;

import com.example.movies.model.Genre;
import com.example.movies.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MovieService {
    List<Movie> listAllMovies();
    Movie findById(Long id);
    Movie create(String title, String description, Genre genre, int year);
    List<Movie> listMoviesByFilters(String title, Genre genre, List<Genre> genres, Integer year, Integer yearFrom, Integer yearTo);
    List<Genre> getAllGemres();
    void addRatingToMovie(Long movieId, int rating);
    void addReviewToMovie(Long movieId, String reviewText);
}
