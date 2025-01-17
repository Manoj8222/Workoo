package com.example.workoo.model;

public class ReviewRequestDTO {
    private Long userId;
    private Long taskerId;
    private String reviewDescription;
    private float rating;

    public ReviewRequestDTO(Long userId, Long taskerId, String reviewDescription, float rating) {
        this.userId = userId;
        this.taskerId = taskerId;
        this.reviewDescription = reviewDescription;
        this.rating = rating;
    }

    public ReviewRequestDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
