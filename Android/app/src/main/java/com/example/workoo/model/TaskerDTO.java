package com.example.workoo.model;

public class TaskerDTO {
    private Long id;
    private String taskerName;
    private Float rating;
    private Integer totalProject;
    private String img;
    private boolean favorite;

    public TaskerDTO(Long id, String taskerName, Float rating, Integer totalProject, String img, boolean favorite) {
        this.id = id;
        this.taskerName = taskerName;
        this.rating = rating;
        this.totalProject = totalProject;
        this.img = img;
        this.favorite = favorite;
    }

    public TaskerDTO() {
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getTotalProject() {
        return totalProject;
    }

    public void setTotalProject(Integer totalProject) {
        this.totalProject = totalProject;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
