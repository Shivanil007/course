package com.example.course.datamodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    private String username;
    private int courseId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionId;

    public  Transaction () {}

    public Transaction(String username, int courseId, String transactionId) {
        this.username = username;
        this.courseId = courseId;
        this.transactionId = transactionId;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
