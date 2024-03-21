package com.example.movies.web;

import com.example.movies.model.Genre;
import com.example.movies.model.Movie;
import com.example.movies.model.MovieDto;
import com.example.movies.model.Review;
import com.example.movies.service.MovieService;
import com.example.movies.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
    private final MovieService movieService;
    private final ReviewService reviewService;

    public MovieController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    //AddMovie(/movies)
    @GetMapping("/movies/add")
    public String showAdd(Model model){
        List<Genre> genres = movieService.getAllGemres();
        model.addAttribute("genres", genres);
        return "form.html";
    }

    @PostMapping("/movies")
    public String create(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam Genre genre,
                         @RequestParam int year){
        this.movieService.create(title, description, genre, year);
        return "redirect:/movies";
    }

    //ListMovies(/movies)
    @GetMapping("/movies")
    public String showMovies( @RequestParam(required = false) String title,
                              @RequestParam(required = false) Genre genre,
                              @RequestParam(required = false) List<Genre> genres,
                              @RequestParam(required = false) Integer year,
                              @RequestParam(required = false) Integer yearFrom,
                              @RequestParam(required = false) Integer yearTo,
                             Model model){
        List<Movie> movies;
        List<Genre> allGemres = movieService.getAllGemres();
        if (title == null && year == null && yearFrom==null && yearTo==null) {
            movies = this.movieService.listAllMovies();
        }else {
            movies = this.movieService.listMoviesByFilters(title,genre, genres, year, yearFrom, yearTo);
        }

//        List<MovieDto> movieDtos = new ArrayList<>();
//        for (Movie movie : movies) {
//            movie = movieService.findById(movie.getId());
//            double averageRating = movie.getAverageRating();
//
//            MovieDto movieDto = new MovieDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenre(), movie.getYear(), averageRating);
//            movieDtos.add(movieDto);
//        }

        model.addAttribute("movies", movies);
        model.addAttribute("genres", allGemres);

        return "list.html";
    }


    //GetMovieDetails(/movies/{id})
    @GetMapping("/movies/{id}")
    public String showDetails(@PathVariable Long id, Model model){
        Movie movie = this.movieService.findById(id);
        List<Review> reviews = this.reviewService.getReviewForMovie(id);
        List<Genre> genres = movieService.getAllGemres();
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        model.addAttribute("genres", genres);

        return "details.html";
    }

    //ListReviews(/reviews)
    @GetMapping("/reviews")
    public String showReview(Model model){
//        List<Movie> movies = this.movieService.listAllMovies();
//        List<Movie> movieList= new ArrayList<>();
//        List<MovieDto> movieDtos = new ArrayList<>();
//
//
//        for (Movie movie : movies) {
//            double averageRating = reviewService.getAverageRatingForMovie(movie.getId());
//            List<Review> reviews = reviewService.getReviewForMovie(movie.getId());
//
//            Movie movie1 = new Movie();
//            movie1.setId(movie.getId());
//            movie1.setTitle(movie.getTitle());
//            movie1.setDescription(movie.getDescription());
//            movie1.setGenre(movie.getGenre());
//            movie1.setYear(movie.getYear());
//            movie1.setAverageRating(averageRating);

//            if (reviews != null) {
//                for (Review review : reviews) {
//                    movie.addReview(review.getReview());
//                }
//            }

//            MovieDto movieDto = new MovieDto();
//            movieDto.setId(movie.getId());
//            movieDto.setTitle(movie.getTitle());
//            movieDto.setDescription(movie.getDescription());
//            movieDto.setGenre(movie.getGenre());
//            movieDto.setYear(movie.getYear());
//            movieDto.setAverageRating(averageRating);
//
//            if (reviews != null) {
//                for (Review review : reviews) {
//                    movieDto.addReview(review.getReview());
//                }
//            }
//
//            movieDtos.add(movieDto);
//        }

//        model.addAttribute("movies", movieDtos);
        return "review.html";
    }

    @GetMapping("/movies/{id}/review")
    public String showReviewForm(@PathVariable Long id, Model model) {
        Movie movie = this.movieService.findById(id);
        Review review= new Review();
        model.addAttribute("movie", movie);
        model.addAttribute("review",review);
        return "review_form.html";
    }

    @PostMapping("/movies/{id}/review")
    public String addReviewToMovie(@PathVariable Long id,
                                   @RequestParam String reviewText,
                                   @RequestParam int rating ) {
        Review review = new Review();
        review.setReview(reviewText);
        review.setRating(rating);
        review.setMovie(movieService.findById(id));
        this.reviewService.addReview(review);
        return "redirect:/movies/"+ id;
    }


}
