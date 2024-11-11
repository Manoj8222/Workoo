package com.android.workoo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date")
    private String date;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "tasker_id")
    private Long taskerId;

    @Column(name = "review_description")
    private String reviewDescription;
}
