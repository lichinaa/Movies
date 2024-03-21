package com.example.movies.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

public class MovieDto {
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private int year;
    private double averageRating;
    private List<String> reviews;

    public MovieDto() {
        this.reviews = new ArrayList<>();
    }

    public MovieDto(Long id, String title, String description, Genre genre, int year, double averageRating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.year = year;
        this.averageRating = averageRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void addReview(String review) {
        this.reviews.add(review);
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}
