package com.example.workoo.model;

public class Scheduled {
    private int image;
    private String taskerName;
    private float rating;
    private long noOfRating;
    private long totalProjects;

    public Scheduled() {
    }

    public Scheduled(int image, String taskerName, float rating, int noOfRating, int totalProjects) {
        this.image = image;
        this.taskerName = taskerName;
        this.rating = rating;
        this.noOfRating = noOfRating;
        this.totalProjects = totalProjects;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public long getNoOfRating() {
        return noOfRating;
    }

    public void setNoOfRating(long noOfRating) {
        this.noOfRating = noOfRating;
    }

    public long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(long totalProjects) {
        this.totalProjects = totalProjects;
    }
}
