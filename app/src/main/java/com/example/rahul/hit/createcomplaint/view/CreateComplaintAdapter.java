package com.example.rahul.hit.createcomplaint.view;

public class CreateComplaintAdapter {
    private String title;
    private String description;
    private String priority;
    private String imageUrl;

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

    CreateComplaintAdapter(String title,String description,String priority,String imageUrl){
        this.title=title;
        this.description=description;
        this.priority=priority;
        this.imageUrl=imageUrl;
    }
}
