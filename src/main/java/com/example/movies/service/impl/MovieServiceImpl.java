package com.example.movies.service.impl;

import com.example.movies.model.Genre;
import com.example.movies.model.Movie;
import com.example.movies.model.Review;
import com.example.movies.model.exceptions.InvalidMovieException;
import com.example.movies.repository.MovieRepository;
import com.example.movies.repository.ReviewRepository;
import com.example.movies.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Movie> listAllMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return this.movieRepository.findById(id).orElseThrow(InvalidMovieException::new);
    }

    @Override
    public Movie create(String title, String description, Genre genre, int year) {
        Movie movie = new Movie(title, description, genre, year);
        return this.movieRepository.save(movie);
    }


    @Override
    public List<Movie> listMoviesByFilters(String title, Genre genre, List<Genre> genres, Integer year, Integer yearFrom, Integer yearTo) {
        String titleLike = "%"+title+"%";

        if (title != null && genres!= null && year != null){
            return this.movieRepository.findAllByTitleLikeAndGenreInAndYear(titleLike, genres, year);
        }
        else if(title != null && genres!= null){
            return this.movieRepository.findAllByTitleLikeAndGenreIn(titleLike, genres);
        }else if (title != null && year != null ) {
            return this.movieRepository.findAllByTitleLikeAndYear(titleLike, year);
        } else if (year != null && genres != null){
            return this.movieRepository.findByYearAndGenreIn(year, genres);
        }else if(yearFrom != null && yearTo!=null && genres!= null){
            return this.movieRepository.findByYearGreaterThanEqualAndYearLessThanEqualAndGenreIn(yearFrom, yearTo, genres);
        }
        else if (title != null && !title.isEmpty()) {
            return this.movieRepository.findAllByTitleLike(titleLike);
        }else if (genres != null){
            return this.movieRepository.findByGenreIn(genres);
        }else if (genre != null){
            return this.movieRepository.findByGenre(genre);
        }else if (year != null) {
            return this.movieRepository.findByYear(year);
        }else if(yearFrom != null && yearTo != null){
            return this.movieRepository.findByYearGreaterThanEqualAndYearLessThanEqual(yearFrom, yearTo);
        }else if(yearFrom!=null){
            return this.movieRepository.findByYearGreaterThanEqual(yearFrom);
        }else if(yearTo!=null){
            return this.movieRepository.findByYearLessThanEqual(yearTo);
        }
        else {
            return this.movieRepository.findAll();
        }
    }

    @Override
    public List<Genre> getAllGemres() {
        return Arrays.asList(Genre.values());
    }

    @Override
    public void addRatingToMovie(Long movieId, int rating) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(InvalidMovieException::new);

        Review reviewNew = new Review();
        reviewNew.setRating(rating);

        double totalRating = movie.getAverageRating()*movie.getNumberOfRatings() + rating;
        movie.setNumberOfRatings(movie.getNumberOfRatings() +1 );
        movie.setAverageRating(totalRating/movie.getNumberOfRatings());

        reviewRepository.save(reviewNew);

        movieRepository.save(movie);
    }

    @Override
    public void addReviewToMovie(Long movieId, String reviewText) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(InvalidMovieException::new);

        Review reviewNew = new Review();
        reviewNew.setReview(reviewText);

        movie.getReviewList().add(reviewNew);
        double totalRating = movie.getAverageRating() * movie.getNumberOfRatings();
        movie.setNumberOfRatings(movie.getNumberOfRatings() + 1);
        movie.setAverageRating((totalRating + reviewNew.getRating()) / movie.getNumberOfRatings());

        movieRepository.save(movie);
        reviewNew.setMovie(movie);
        reviewRepository.save(reviewNew);
    }


}
