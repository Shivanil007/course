package com.example.course.controllers;

import com.example.course.datamodels.Course;
import com.example.course.datamodels.Transaction;
import com.example.course.repositories.CourseRepository;
import com.example.course.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends ApplicationController {

    private CourseRepository courseRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public UserController(CourseRepository courseRepository, TransactionRepository transactionRepository) {
        this.courseRepository = courseRepository;
        this.transactionRepository = transactionRepository;
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
    @PostMapping("/perform-transaction")
    public String transaction(@ModelAttribute Course course, Principal principal) {
        Transaction transaction = new Transaction();
        transaction.setUsername(principal.getName());
        transaction.setCourseId(course.getCourseId());
        if (this.transactionRepository.findTransactionByCourseId(course.getCourseId()) !=  null) {

            return "purchaseNow";
        }
        this.transactionRepository.save(transaction);
        return "redirect:/user/home";
    }

}
