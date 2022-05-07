package com.example.course.repositories;

import com.example.course.datamodels.Feedback;
import com.example.course.datamodels.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository  extends JpaRepository<Feedback, Integer> {
    Feedback findFeedbackByFeedbackId ( int feedbackId);
    Feedback findFeedbackByByCourseId(int courseId);

    List<Feedback> findByFeedback(String feedback);


}

