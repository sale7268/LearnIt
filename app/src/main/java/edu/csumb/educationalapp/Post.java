package edu.csumb.educationalapp;

public class Post{

    private String objectId;
    private String title;
    private String content;
    private String createdBy;

    public Post() {}

    public Post(String objectId, String title, String content, String createdBy) {
        this.objectId = objectId;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Object ID:" + objectId + "\n" + "Title: " + title + "\n" + "Content:  " + content + "\n" +
                "Created By: " + createdBy + "\n";
    }

}
