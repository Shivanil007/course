package com.example.course.datamodels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    private int courseId;
    private String courseName;
    private String courseDescription;
    private String author;
    private int price;

    public Course() {}

    public Course(int courseId, String courseName, String courseDescription, String author, int price) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.author = author;
        this.price = price;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
