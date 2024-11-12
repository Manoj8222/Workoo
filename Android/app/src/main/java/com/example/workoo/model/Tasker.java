package com.example.workoo.model;

import java.math.BigDecimal;

public class Tasker {
    private Long id;
    private String taskerName;
    private Long phoneNumber;
    private String password; // You may exclude this field for security
    private byte[] img; // Optional image data
    private String skill;
    private String description;
    private Long fair;
    private String location;
    private BigDecimal rating; // Optional
    private String review; // Optional
    private Long totalProject; // Optional

    // Constructor
    public Tasker(Long id, String taskerName, Long phoneNumber, String password, byte[] img, String skill,
                  String description, Long fair, String location, BigDecimal rating, String review, Long totalProject) {
        this.id = id;
        this.taskerName = taskerName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.img = img;
        this.skill = skill;
        this.description = description;
        this.fair = fair;
        this.location = location;
        this.rating = rating;
        this.review = review;
        this.totalProject = totalProject;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFair() {
        return fair;
    }

    public void setFair(Long fair) {
        this.fair = fair;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Long getTotalProject() {
        return totalProject;
    }

    public void setTotalProject(Long totalProject) {
        this.totalProject = totalProject;
    }
}
