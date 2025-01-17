package com.example.workoo.model;

public class SearchTaskerList {
    String taskerName;
    float rating;
    int numberOfReview;
    String location;
    String description;
    Integer image;
    int fair;

    public SearchTaskerList(String taskerName, float rating, int numberOfReview, String location, String description, Integer image,int fair) {
        this.taskerName = taskerName;
        this.rating = rating;
        this.numberOfReview = numberOfReview;
        this.location = location;
        this.description = description;
        this.image = image;
        this.fair = fair;
    }

    public int getFair() {
        return fair;
    }

    public void setFair(int fair) {
        this.fair = fair;
    }

    public String getTaskerName() {
        return taskerName;
    }

    public void setTaskerName(String taskerName) {
        this.taskerName = taskerName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumberOfReview() {
        return numberOfReview;
    }

    public void setNumberOfReview(int numberOfReview) {
        this.numberOfReview = numberOfReview;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
