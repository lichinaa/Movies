package com.example.movies.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private int year;
    private double averageRating;
    private int numberOfRatings;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    public Movie(){
    }

    public Movie(Long id, String title, String description, Genre genre, int year) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.year = year;
    }

    public Movie(String title, String description, Genre genre, int year) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.year = year;
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

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public List<Review> getReviewList() {
        return reviews;
    }

    public void setReviewList(List<Review> reviews) {
        this.reviews = reviews;
    }
//    public void addReview(String review) {
//        this.reviews.add(review);
//    }

}
