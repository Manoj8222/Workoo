package com.android.workoo.dto;

import java.time.LocalDateTime;


public class ReviewResponseDTO {

    private Long id;
    private String userName;
    private String profilePhoto;
    private LocalDateTime date;
    private Double rating;
    private String reviewDescription;

    public ReviewResponseDTO(Long id, String userName, String profilePhoto, LocalDateTime date, Double rating, String reviewDescription) {
        this.id = id;
        this.userName = userName;
        this.profilePhoto = profilePhoto;
        this.date = date;
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }

    public ReviewResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    // Getters and Setters
}