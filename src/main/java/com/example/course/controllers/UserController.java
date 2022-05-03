package com.example.course.controllers;

import com.example.course.datamodels.Course;
import com.example.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends ApplicationController {

    private CourseRepository courseRepository;

    @Autowired
    public UserController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/home")
    public ModelAndView userHome() {
        ModelAndView modelAndView = new ModelAndView();
        List<Course> courses = this.courseRepository.findAll();
        modelAndView.addObject("courses", courses);
        modelAndView.setViewName("userHome");
        return modelAndView;
    }

    @GetMapping("/purchase-course")
    public ModelAndView purchase(@RequestParam(name = "id") String courseId) {
        Course course = this.courseRepository.findCourseByCourseId(Integer.parseInt(courseId.trim()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("course", course);
        modelAndView.setViewName("purchaseNow");
        return modelAndView;
    }

}
