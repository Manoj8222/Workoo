package com.example.workoo.model;

public class BookingResponse {
    private Long id;
    private Long userId;
    private Long taskerId;
    private String bookingDate;
    private String bookingTime;
    private Boolean taskCompleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskerId() {
        return taskerId;
    }

    public void setTaskerId(Long taskerId) {
        this.taskerId = taskerId;
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

    public Boolean getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(Boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }
}
