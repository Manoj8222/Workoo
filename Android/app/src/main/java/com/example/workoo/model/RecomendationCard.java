package com.example.workoo.model;

import java.math.BigDecimal;

public class RecomendationCard {
    String taskerName;
    String skills;
    String fair;
    double rating;
    String totalRating;
    Integer img;

    public RecomendationCard(String taskerName, String skills, String fair, double rating, String totalRating,Integer img) {
        this.taskerName = taskerName;
        this.skills = skills;
        this.fair = fair;
        this.rating = rating;
        this.totalRating = totalRating;
        this.img = img;
    }


    public String getTaskerName() {
        return taskerName;
    }

    public void setTaskerName(String taskerName) {
        this.taskerName = taskerName;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getFair() {
        return fair;
    }

    public void setFair(String fair) {
        this.fair = fair;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(String totalRating) {
        this.totalRating = totalRating;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
