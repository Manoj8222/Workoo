package com.example.workoo.model;

public class ReviewResponseDTO {
    private Long reviewId;
    private Long userId;
    private Long taskerId;
    private String reviewDescription;
    private float rating;

    public ReviewResponseDTO(Long reviewId, Long userId, Long taskerId, String reviewDescription, float rating) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.taskerId = taskerId;
        this.reviewDescription = reviewDescription;
        this.rating = rating;
    }

    public ReviewResponseDTO() {
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTaskerId(Long taskerId) {
        this.taskerId = taskerId;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
