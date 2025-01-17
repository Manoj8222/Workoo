package com.example.workoo.model;

public class TaskerReviewModel {
    String reviewerName;
    Integer image;
    float rating;
    String reviewDescription;

    public TaskerReviewModel(String reviewerName, Integer image, float rating,String reviewDescription) {
        this.reviewerName = reviewerName;
        this.image = image;
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return reviewDescription;
    }

    public void setReview(String review) {
        this.reviewDescription = review;
    }
}
