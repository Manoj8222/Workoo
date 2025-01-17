package com.android.workoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingHistoryResponseDTO {
    private Long bookingId;
    private Long userId;
    private String bookingDate;
    private String bookingTime;
    private boolean taskCompleted;
    private Long fair;
    private Long taskerId;
    private String taskerName;
    private String taskerImage; // Assuming image will be in base64 or URL format
    private BigDecimal taskerRating;
    private Long taskerTotalProjects;


    // Getters and Setters
}