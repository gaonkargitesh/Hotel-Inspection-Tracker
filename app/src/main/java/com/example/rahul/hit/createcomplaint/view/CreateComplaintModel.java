package com.example.rahul.hit.createcomplaint.view;

public class CreateComplaintModel {
    private String title;
    private String description;
    private String priority;
    private String imageUrl;
    private String id;
    private String email;
    private String status;
    private String creator;

    public CreateComplaintModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    CreateComplaintModel(String title, String description, String priority, String imageUrl, String id, String status, String creator) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.imageUrl = imageUrl;
        this.id = id;
        this.status = status;
        this.creator=creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
