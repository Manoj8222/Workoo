package com.example.workoo.model;

public class FavoriteModel {
    String taskerName;
    float rating;
    int totalReview;
    int totalJob;
    Integer taskerImg;

    public FavoriteModel(String taskerName, float rating, int totalReview, int totalJob,Integer taskerImg) {
        this.taskerName = taskerName;
        this.rating = rating;
        this.totalReview = totalReview;
        this.totalJob = totalJob;
        this.taskerImg = taskerImg;
    }

    public Integer getTaskerImg() {
        return taskerImg;
    }

    public void setTaskerImg(Integer taskerImg) {
        this.taskerImg = taskerImg;
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

    public int getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(int totalReview) {
        this.totalReview = totalReview;
    }

    public int getTotalJob() {
        return totalJob;
    }

    public void setTotalJob(int totalJob) {
        this.totalJob = totalJob;
    }
}
