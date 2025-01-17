package com.example.workoo.model;


public class Review {
    private Long id;
    private Long userId;
    private String date;
    private Double rating;
    private Long taskerId;
    private String reviewDescription;

    public Review() {
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getTaskerId() {
        return taskerId;
    }

    public void setTaskerId(Long taskerId) {
        this.taskerId = taskerId;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }
}
