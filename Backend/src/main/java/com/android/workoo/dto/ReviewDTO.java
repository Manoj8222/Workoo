package com.android.workoo.dto;

import java.time.LocalDate;

public class ReviewDTO {
    private Long id;
    private String userName;
    private String profilePhoto;
    private LocalDate date;
    private Double rating;
    private String description;

    public ReviewDTO(Long id, String userName, String profilePhoto, LocalDate date, Double rating, String description) {
        this.id = id;
        this.userName = userName;
        this.profilePhoto = profilePhoto;
        this.date = date;
        this.rating = rating;
        this.description = description;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters and Setters
}