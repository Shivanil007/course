package com.example.course.repositories;

import com.example.course.datamodels.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findCourseByCourseId(int courseId);
}
