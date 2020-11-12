package edu.csumb.educationalapp;

public class Post{

    private String title;
    private String content;
    private String createdBy;

    public Post() {}

    public Post(String title, String content, String createdBy) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
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
        return "Title: " + title + "\n" + "Content:  "+ content + "\n";
    }

}
