package com.example.workoo.model;

public class AllService {
    Integer image;
    String name;

    public AllService(Integer image, String name) {
        this.image = image;
        this.name = name;
    }

    public AllService() {
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
