package com.example.course.controllers;

import com.example.course.datamodels.Course;
import com.example.course.datamodels.Feedback;
import com.example.course.datamodels.Transaction;
import com.example.course.repositories.CourseRepository;
import com.example.course.repositories.FeedbackRepository;
import com.example.course.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends ApplicationController {

    private CourseRepository courseRepository;
    private TransactionRepository transactionRepository;
    private FeedbackRepository feedbackRepository;

    @Autowired
    public UserController(CourseRepository courseRepository, TransactionRepository transactionRepository, FeedbackRepository feedbackRepository) {
        this.courseRepository = courseRepository;
        this.transactionRepository = transactionRepository;
        this.feedbackRepository = feedbackRepository;
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
        if (this.transactionRepository.findTransactionByCourseId(course.getCourseId()) != null) {
            return "purchaseNow";
        }
        this.transactionRepository.save(transaction);
        return "redirect:/user/home";
    }

    @GetMapping("/view-my-course")
    public String viewMyCourses(Principal principal, Model model) {
        List<Transaction> courseIds = this.transactionRepository.findTransactionsByUsername(principal.getName());
        List<Course> courses = new ArrayList<>();
        for (Transaction transaction : courseIds) {
            Course course = this.courseRepository.findCourseByCourseId(transaction.getCourseId());
            courses.add(course);
        }
        model.addAttribute("courses", courses);
        return "viewMyCourses";
    }

    @PostMapping("/save-feedback")
    public String feedback(@ModelAttribute Feedback feedback, Principal principal) {
        feedback.setUsername(principal.getName());
        this.feedbackRepository.save(feedback);
        return "redirect:/user/home";
    }

    @GetMapping("/feedback")
    public ModelAndView getfeedback(@RequestParam(name = "id") String courseId) {
        ModelAndView modelAndView = new ModelAndView();
        Feedback feedback = new Feedback();
        feedback.setCourseId(Integer.parseInt(courseId.trim()));
        modelAndView.addObject("feedback", feedback);
        modelAndView.setViewName("feedback");
        return modelAndView;
    }
}
