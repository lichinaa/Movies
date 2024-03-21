package com.example.movies.service;

import com.example.movies.model.Review;

import java.util.List;

public interface ReviewService {
    Review findById(Long id);
    List<Review> listAllReviews();
    Review create(String reviewText, int rating);
    Review addReview(Review review);
    List<Review> getReviewForMovie(Long movieId);

    double getAverageRatingForMovie(Long movieId);

}
