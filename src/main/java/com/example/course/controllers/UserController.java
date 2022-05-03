package com.example.course.controllers;

import com.example.course.datamodels.Course;
import com.example.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        System.out.println(courses.size());
        courses.stream().forEach(x -> System.out.println(x.getCourseName()));
        modelAndView.addObject("courses", courses);
        modelAndView.setViewName("userHome");
        return modelAndView;
    }


}
