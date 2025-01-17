package com.android.workoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingWithTaskerDetailsDTO {

    private Long bookingId;
    private Long userId;
    private String bookingDate;
    private String bookingTime;

    // Tasker Details
    private Long taskerId;
    private String taskerName;
    private byte[] taskerImage;
    private Double taskerRating;
    private Integer totalProjects;

    // Getters and Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Long getTaskerId() {
        return taskerId;
    }

    public void setTaskerId(Long taskerId) {
        this.taskerId = taskerId;
    }

    public String getTaskerName() {
        return taskerName;
    }

    public void setTaskerName(String taskerName) {
        this.taskerName = taskerName;
    }

    public byte[] getTaskerImage() {
        return taskerImage;
    }

    public void setTaskerImage(byte[] taskerImage) {
        this.taskerImage = taskerImage;
    }

    public Double getTaskerRating() {
        return taskerRating;
    }

    public void setTaskerRating(Double taskerRating) {
        this.taskerRating = taskerRating;
    }

    public Integer getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(Integer totalProjects) {
        this.totalProjects = totalProjects;
    }
}

