package com.example.workoo.model;

import java.math.BigInteger;

public class UserResponse {
    private Long id;
    private String userName;
    private BigInteger phoneNumber;
    private byte[] img;
    private String location;

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

    public BigInteger getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(BigInteger phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
