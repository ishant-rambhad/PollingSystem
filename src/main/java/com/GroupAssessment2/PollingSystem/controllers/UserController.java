package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.Poll;
import com.GroupAssessment2.PollingSystem.models.UserDetails;
import com.GroupAssessment2.PollingSystem.services.UserService;
import com.GroupAssessment2.PollingSystem.services.PollResultService;
import com.GroupAssessment2.PollingSystem.services.PollService;
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

    @Autowired
    private PollService pollService;

    @Autowired
    private PollResultService pollResultService; // Autowire PollResultService


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

        boolean isAuthenticated = userService.authenticateUser(userCred.getEmail(), userCred.getPassword(),
                userCred.getName());

        if (isAuthenticated) {
            return "redirect:/users/dashboard/" + userCred.getEmail(); // Redirect to user dashboard on success
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "User_Login"; // Re-render login page with error
        }
    }

    @GetMapping("/dashboard/{email}")
    public String showUserDashboard(@PathVariable String email, Model model) {
        model.addAttribute("email", email);
        return "user_dashboard"; // Thymeleaf or JSP should render this
    }

    @GetMapping("/polls/{email}")
    public String showUserPolls(@PathVariable String email, Model model) {
        List<Poll> polls = pollService.getAllPolls();
        System.out.println(polls); // Fetch all polls from the database
        model.addAttribute("email", email);
        model.addAttribute("polls", polls); // Add the polls data to the model
        return "Polling"; // Render the Polling view
    }
    @GetMapping("/polls/{email}/vote")
    public String vote(@PathVariable String email, Model model) {
        List<Poll> polls = pollService.getAllPolls(); // Fetch all polls from the database
        model.addAttribute("email", email);
        model.addAttribute("polls", polls); // Add the polls data to the model
        return "vote_success"; // Render the Polling view
    }

    @PostMapping("/polls/{email}/vote")
    public String submitResult(@RequestParam String pollOption, @RequestParam Long pollId, Model model, @PathVariable String email) {
        model.addAttribute("email", email);

        try {
            System.out.println(pollId);
            System.out.println(email);
            System.out.println(pollOption);
            pollResultService.submitPollResult(email, pollOption, pollId); // Call submitPollResult
            model.addAttribute("message", "You voted for: " + pollOption);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            model.addAttribute("error", "Invalid option selected");
            return "vote_error"; // Return an error page or message
        } catch (Exception e) {
            System.out.println(e);
            model.addAttribute("error", e.getMessage());
            return "vote_error"; // Return an error page or message
        }

        return "vote_success"; // Return a success page or message
    }

    // Get a list of all users
    @GetMapping("/all")
    public ModelAndView getAllUsers() {
        List<UserDetails> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("all_users");
        modelAndView.addObject("users", users); // Pass the list of users to the view
        return modelAndView;
    }
}