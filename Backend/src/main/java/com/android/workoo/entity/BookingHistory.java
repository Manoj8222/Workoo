package com.android.workoo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "booking_history")
@Data
public class BookingHistory {
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


    @Column(name = "task_completed")
    private boolean taskCompleted;

    @Column(name = "booking_fair")
    private Long fair;
}
