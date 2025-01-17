package com.android.workoo.model;

import java.math.BigDecimal;

public class TaskerFavoriteDTO {
    private Long id;
    private String taskerName;
    private BigDecimal rating;
    private Long totalProject;
    private String img;
    private boolean isFavorite;

    public TaskerFavoriteDTO(Long id, String taskerName, BigDecimal rating, Long totalProject, String img, boolean isFavorite) {
        this.id = id;
        this.taskerName = taskerName;
        this.rating = rating;
        this.totalProject = totalProject;
        this.img = img;
        this.isFavorite = isFavorite;
    }

    public TaskerFavoriteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskerName() {
        return taskerName;
    }

    public void setTaskerName(String taskerName) {
        this.taskerName = taskerName;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Long getTotalProject() {
        return totalProject;
    }

    public void setTotalProject(Long totalProject) {
        this.totalProject = totalProject;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
