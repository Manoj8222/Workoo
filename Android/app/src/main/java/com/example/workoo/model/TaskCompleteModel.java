package com.example.workoo.model;

public class TaskCompleteModel {
    private int taskerId;
    private String taskerName;
    private String bookingDate;
    private String bookingTime;
    private Integer image;

    public TaskCompleteModel(int taskerId, String taskerName, String bookingDate, String bookingTime, Integer image) {
        this.taskerId = taskerId;
        this.taskerName = taskerName;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.image = image;
    }

    public int getTaskerId() {
        return taskerId;
    }

    public void setTaskerId(int taskerId) {
        this.taskerId = taskerId;
    }

    public String getTaskerName() {
        return taskerName;
    }

    public void setTaskerName(String taskerName) {
        this.taskerName = taskerName;
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

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
