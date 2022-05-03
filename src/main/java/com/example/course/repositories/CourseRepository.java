package com.example.course.repositories;

import com.example.course.datamodels.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findCourseByCourseId(int courseId);

    List<Course> findAll();
}
