package com.android.workoo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "tasker")
@Data
public class Tasker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tasker_name",nullable = false)
    private String taskerName;

    @Column(name = "phone_number",nullable = false)
    private BigInteger phoneNumber;

    @Column(name = "password",nullable = false)
    private String password;

    @Lob
    @Column(name = "img")
    private byte[] img;

    @Column(name = "skill",nullable = false)
    private String skill;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "fair",nullable = false)
    private Long fair;

    @Column(name = "city")
    private String city;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "review")
    private String review;

    @Column(name = "total_project")
    private Long totalProject;
}
