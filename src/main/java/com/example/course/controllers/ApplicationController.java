package com.example.course.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

import com.example.course.datamodels.User;
import com.example.course.repositories.UserRepository;
import com.example.course.service.MyUserDetailsService;
import com.example.course.utils.UserRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
public class ApplicationController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @GetMapping({"/", "/user"})
    public RedirectView home(Principal principal) {
        User user = this.userRepository.findByUsername(principal.getName());
        System.out.println("I am before if else");
        System.out.println(user.getRole());
        if (user.getRole().equals(UserRoles.USER)) {
            return new RedirectView("/user/home");
        } else {
            System.out.println("I am in login api");
            return new RedirectView("/admin/home");
        }
    }

    @RequestMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("this is login");
        if (! (auth instanceof AnonymousAuthenticationToken)) {
            System.out.println("this is loginout");
            return "redirect:/user";

        }
        return "login";
    }

    @RequestMapping("/logout-success")
    public String logout() {
        return "login";
    }

    @RequestMapping(value="/signup")
    public String signup(User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (! (auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/user/";
        }
        return "signup";
    }
    

    @PostMapping("/signup")
    public String addUser(@ModelAttribute User user, ModelMap modelMap) {
        try {
            this.userDetailsService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException uException) {
            try {
                this.userDetailsService.registerUser(user);
                return "redirect:/user/home";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        modelMap.addAttribute("error_message", "Registration Unsuccessful");
        return "signup";
    }
}
