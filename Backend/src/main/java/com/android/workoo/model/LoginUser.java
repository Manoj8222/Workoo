package com.android.workoo.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class LoginUser {
    private BigInteger phone_number;
    private String password;
}
