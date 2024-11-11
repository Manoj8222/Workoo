package com.android.workoo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Table(name = "user")
@Data
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        @Column(name = "user_name")
        private String userName;
        @Column(name = "phone_number")
        private BigInteger phoneNumber;
        @Lob
        @Column(name = "img")
        private byte[] img;
        @Column(name = "location")
        private String location;
        @Column(name = "password")
        private String password;

//    public User(Long phoneNumber, String password){
//        this.phoneNumber = phoneNumber;
//        this.password = password;
//    }
//
//    public User(String userName, Long phoneNumber, String img) {
//        this.userName = userName;
//        this.phoneNumber = phoneNumber;
//        this.img = img;
//    }

}
