package edu.csumb.educationalapp;

import java.util.ArrayList;
import java.util.List;


public class Post{

    private String objectId;
    private String title;
    private String content;
    private String createdBy;
    private ArrayList<String> stepsList;

    public Post() {}

    public Post(String objectId, String title, String content, String createdBy,ArrayList<String> stepsList) {
        this.objectId = objectId;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.stepsList = stepsList;
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

    public ArrayList<String> getStepsList() {
        return stepsList;
    }

    public void setStepsList(ArrayList<String> stepsList) {
        this.stepsList = stepsList;
    }

    //use getStepContent inside the stepList
    public static String concatStrings(ArrayList<String>stepsList) {
        StringBuilder sb = new StringBuilder();
        for(String s:stepsList) {
            sb.append(s + " \n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if(!concatStrings(stepsList).isEmpty()){
            return "Title: " + title + "\n" + "Content:  " + content + "\n" +
                    "Created By: " + createdBy + "\n" + concatStrings(stepsList) + "\n";
        }
        return "Title: " + title + "\n" + "Content:  " + content + "\n" +
                "Created By: " + createdBy + "\n";
    }
}
