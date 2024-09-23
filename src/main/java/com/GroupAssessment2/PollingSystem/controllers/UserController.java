package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.UserDetails;
import com.GroupAssessment2.PollingSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Show registration form
    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("user_registration");
        modelAndView.addObject("user", new UserDetails());
        return modelAndView;
    }

    // Handle registration of users
    @PostMapping("/register")
    public ModelAndView registerUser(@Valid @ModelAttribute("user") UserDetails userDetails, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("user_registration");

        if (result.hasErrors()) {
            modelAndView.addObject("error", "Validation errors occurred");
            return modelAndView;
        }

        // Check if password and confirmPassword match
        if (!userDetails.getPassword().equals(userDetails.getConfirmPassword())) {
            modelAndView.addObject("error", "Passwords do not match");
            return modelAndView;
        }

        try {
            userService.registerUser(userDetails);
            modelAndView.setViewName("registration_success");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", e.getMessage());
        }

        return modelAndView;
    }

    // Show login form
    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("User_Login");
        modelAndView.addObject("userCred", new UserDetails());
        return modelAndView;
    }

    // Handle user login
    @PostMapping("/login")
    public String authenticateUser(@ModelAttribute("userCred") UserDetails userCred, Model model) {
        System.out.println("Email: " + userCred.getEmail()); // Debugging
        System.out.println("Password: " + userCred.getPassword());
        System.out.println("Username: " + userCred.getName());

        boolean isAuthenticated = userService.authenticateUser(userCred.getEmail(), userCred.getPassword(), userCred.getName());

        if (isAuthenticated) {
            return "redirect:/users/dashboard"; // Redirect to user dashboard on success
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "User_Login"; // Re-render login page with error
        }
    }

    @GetMapping("/dashboard")
    public String showUserDashboard() {
        return "user_dashboard"; // This would be your dashboard HTML page
    }

    

    // Get a list of all users
    @GetMapping("/all")
    public List<UserDetails> getAllUsers() {
        return userService.getAllUsers();
    }
}
