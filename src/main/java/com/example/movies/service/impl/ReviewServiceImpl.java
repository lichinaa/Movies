package com.example.movies.service.impl;

import com.example.movies.model.Review;
import com.example.movies.model.exceptions.InvalidRatingException;
import com.example.movies.repository.ReviewRepository;
import com.example.movies.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id).orElseThrow(InvalidRatingException::new);
    }

    @Override
    public List<Review> listAllReviews() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Review create(String reviewText, int rating) {
        Review review = new Review();
        review.setReview(reviewText);
        review.setRating(rating);
        return reviewRepository.save(review);
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewForMovie(Long movieId) {
        return this.reviewRepository.findByMovieId(movieId);
    }

    @Override
    public double getAverageRatingForMovie(Long movieId) {
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        int sum = reviews.stream().mapToInt(Review::getRating).sum();
        return (double) sum / reviews.size();
    }

}
