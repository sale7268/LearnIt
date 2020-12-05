package edu.csumb.educationalapp;

public class Comment {

    private String createdBy;
    private String content;

    public Comment(String createdBy, String content) {
        this.createdBy = createdBy;
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "createdBy='" + createdBy + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
