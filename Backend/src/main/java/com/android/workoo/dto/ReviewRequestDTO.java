package com.android.workoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
    private Long userId;
    private Long taskerId;
    private String reviewDescription;
    private float rating;

    // Getters and Setters
}