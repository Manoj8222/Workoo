package com.example.workoo.model;

import java.math.BigInteger;

public class LoginTasker {

    private BigInteger phone_number;
    private String password;

    // Constructors
    public LoginTasker() {
    }

    public LoginTasker(BigInteger phone_number, String password) {
        this.phone_number = phone_number;
        this.password = password;
    }

    // Getters and Setters
    public BigInteger getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(BigInteger phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
