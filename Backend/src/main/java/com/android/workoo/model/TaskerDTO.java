package com.android.workoo.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TaskerDTO {
    private Long id;
    private String taskerName;
    private BigInteger phoneNumber;
    private String skill;
    private String location;
    private BigDecimal rating;
    private Integer totalProject;
    private String description;
    private String review;
    private Integer fair;
    private String city;
    private byte[] img;
    private boolean favorite;

    public Integer getFair() {
        return fair;
    }

    public void setFair(Integer fair) {
        this.fair = fair;
    }

    public String getCity() {
        return city;
    }

    public BigInteger getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(BigInteger phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskerName() {
        return taskerName;
    }

    public void setTaskerName(String taskerName) {
        this.taskerName = taskerName;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getTotalProject() {
        return totalProject;
    }

    public void setTotalProject(Integer totalProject) {
        this.totalProject = totalProject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getFar() {
        return fair;
    }

    public void setFar(Integer far) {
        this.fair = far;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setCity(String city) {
    }
}
