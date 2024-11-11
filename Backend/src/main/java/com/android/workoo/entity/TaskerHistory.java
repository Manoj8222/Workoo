package com.android.workoo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasker_history")
@Data
public class TaskerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "tasker_id")
    private Long taskerId;

    @Column(name = "booking_date")
    private String bookingDate;

    @Column(name = "booking_time")
    private String bookingTime;

    @Lob
    @Column(name = "img")
    private byte[] img;
}
