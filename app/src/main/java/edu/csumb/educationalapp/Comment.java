package edu.csumb.educationalapp;

public class Comment {

    private String postID;
    private String createdBy;
    private String content;

    public Comment(String createdBy, String content,String postID) {
        this.createdBy = createdBy;
        this.content = content;
        this.postID = postID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
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
        return "Comment By: " + createdBy + "\n" + content + "\n";
    }
}
