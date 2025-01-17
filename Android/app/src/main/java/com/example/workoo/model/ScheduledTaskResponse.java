package com.example.workoo.model;

public class ScheduledTaskResponse {
    private Long bookingId;
    private Long userId;
    private String bookingDate;
    private String bookingTime;
    private Boolean taskCompleted;
    private Long taskerId;
    private String taskerName;
    private String taskerImage;
    private Float taskerRating;
    private Long taskerTotalProjects;
    private Long fair;

    public Long getFair() {
        return fair;
    }

    public void setFair(Long fair) {
        this.fair = fair;
    }

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

    public Boolean getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(Boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
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

    public Float getTaskerRating() {
        return taskerRating;
    }

    public void setTaskerRating(Float taskerRating) {
        this.taskerRating = taskerRating;
    }

    public Long getTaskerTotalProjects() {
        return taskerTotalProjects;
    }

    public void setTaskerTotalProjects(Long taskerTotalProjects) {
        this.taskerTotalProjects = taskerTotalProjects;
    }
}
