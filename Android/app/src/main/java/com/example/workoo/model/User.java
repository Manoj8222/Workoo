package com.example.workoo.model;

public class User {
    private Long id;
    private String userName;
    private Long phoneNumber;
    private String location;
    private String password; // You may exclude this field in some cases for security
    private byte[] img; // Optional image data

    // Constructor
    public User(Long id, String userName, Long phoneNumber, String location, String password, byte[] img) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.password = password;
        this.img = img;
    }

    // Getters and Setters
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
}
