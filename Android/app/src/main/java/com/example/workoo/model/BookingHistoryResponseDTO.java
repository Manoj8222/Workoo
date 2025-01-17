package com.example.workoo.model;

import java.math.BigDecimal;

public class BookingHistoryResponseDTO {
    private Long bookingId;
    private Long userId;
    private String bookingDate;
    private String bookingTime;
    private boolean taskCompleted;
    private Long fair;
    private Long taskerId;
    private String taskerName;
    private String taskerImage; // Base64 or URL
    private BigDecimal taskerRating;
    private Long taskerTotalProjects;

    public BookingHistoryResponseDTO(Long bookingId, Long userId, String bookingDate, String bookingTime, boolean taskCompleted, Long fair, Long taskerId, String taskerName, String taskerImage, BigDecimal taskerRating, Long taskerTotalProjects) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.taskCompleted = taskCompleted;
        this.fair = fair;
        this.taskerId = taskerId;
        this.taskerName = taskerName;
        this.taskerImage = taskerImage;
        this.taskerRating = taskerRating;
        this.taskerTotalProjects = taskerTotalProjects;
    }

    public BookingHistoryResponseDTO() {
    }

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

    public boolean isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public Long getFair() {
        return fair;
    }

    public void setFair(Long fair) {
        this.fair = fair;
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

    public String getTaskerImage() {
        return taskerImage;
    }

    public void setTaskerImage(String taskerImage) {
        this.taskerImage = taskerImage;
    }

    public BigDecimal getTaskerRating() {
        return taskerRating;
    }

    public void setTaskerRating(BigDecimal taskerRating) {
        this.taskerRating = taskerRating;
    }

    public Long getTaskerTotalProjects() {
        return taskerTotalProjects;
    }

    public void setTaskerTotalProjects(Long taskerTotalProjects) {
        this.taskerTotalProjects = taskerTotalProjects;
    }
    // Getters and Setters
}
