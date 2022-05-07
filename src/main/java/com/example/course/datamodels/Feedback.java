package com.example.course.datamodels;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String feedbackId;
    private String username;
    private int courseId;
    private String feedbackDescription;

    public Feedback () {}

    public Feedback(String username, int courseId, String feedbackDescription, String feedbackId) {
        this.username = username;
        this.courseId = courseId;
        this.feedbackDescription = feedbackDescription;
        this.feedbackId = feedbackId;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }
}
