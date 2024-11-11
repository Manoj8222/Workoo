package com.android.workoo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "checkout")
@Data
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", length = 45)
    private String userId;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "tasker_id")
    private Long taskerId;
}