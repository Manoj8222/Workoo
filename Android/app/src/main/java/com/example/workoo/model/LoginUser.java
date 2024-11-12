package com.example.workoo.model;

import java.math.BigInteger;

public class LoginUser {

    private BigInteger phone_number;
    private String password;

    // Constructors
    public LoginUser() {
    }


    public LoginUser(BigInteger phone_number, String password) {
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
