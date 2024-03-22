package com.example.movies.repository;

import com.example.movies.model.Genre;
import com.example.movies.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByTitleLike(String title);
    List<Movie> findByGenre(Genre genre);
    List<Movie> findByGenreIn(List<Genre> genres);
    List<Movie> findByYear(Integer year);
    List<Movie> findByYearGreaterThanEqual(Integer yearFrom);
    List<Movie> findByYearLessThanEqual(Integer yearTo);
    List<Movie> findByYearGreaterThanEqualAndYearLessThanEqual(Integer yearFrom, Integer yearTo);
    List<Movie> findAllByTitleLikeAndGenreInAndYear(String title, List<Genre> genres, Integer year);
    List<Movie> findAllByTitleLikeAndGenreIn(String title, List<Genre> genres);
    List<Movie> findAllByTitleLikeAndYear(String title, Integer year);
    List<Movie> findByYearAndGenreIn(Integer year, List<Genre> genres);
    List<Movie> findByYearGreaterThanEqualAndYearLessThanEqualAndGenreIn(Integer yearFrom, Integer yearTo, List<Genre> genres);

}
