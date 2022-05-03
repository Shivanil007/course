package com.example.course.datamodels;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    private String userId;
    private String courseId;
    private String transactionId;

    public  Transaction () {}
    public Transaction (int userId, int courseId, int transactionId) {
        this.userId = userId;
        this.courseId = courseId;
        this.transactionId = transactionId;


    }
    public int getUserId() { return this.userId;}
    public void setUserId(String userid) {
        this.userId = userid;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String password) {
        this.courseId = courseId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionid) {
        this.transactionId = transactionId;
    }



}
