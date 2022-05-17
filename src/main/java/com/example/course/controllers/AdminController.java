package com.example.course.controllers;

import com.example.course.datamodels.Course;
import com.example.course.datamodels.Transaction;
import com.example.course.repositories.CourseRepository;
import com.example.course.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController  {
    private CourseRepository courseRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public AdminController (CourseRepository courseRepository, TransactionRepository transactionRepository) {
        this.courseRepository = courseRepository;
        this.transactionRepository = transactionRepository;
    }
    @GetMapping("/home")
    public ModelAndView userHome() {
        ModelAndView modelAndView = new ModelAndView();
        List<Course> courses = this.courseRepository.findAll();
        modelAndView.addObject("courses", courses);
        modelAndView.setViewName("admin");
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
    @GetMapping("/")
    public ModelAndView addCourse() {
        ModelAndView modelAndView = new ModelAndView();
        List<
                modelAndView.addObject("")
                modelAndView.setViewName("addcourse");
        return modelAndView;
    }

}
