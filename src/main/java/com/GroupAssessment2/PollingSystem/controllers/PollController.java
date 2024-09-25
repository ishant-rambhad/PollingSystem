package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.Poll;
import com.GroupAssessment2.PollingSystem.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller 
@RequestMapping("/admin")
public class PollController {

    @Autowired
    private PollService pollService;

    // Serve the poll creation form
    @GetMapping("/create_polling")
    public String showCreatePollForm(Model model) {
        model.addAttribute("poll", new Poll());  // Add an empty Poll object to bind form data
        return "Create_polling";  // This should be the name of your HTML file without extension
    }

    // Handle form submission and create poll
    @PostMapping("/PollCreate")
    public String createPoll(@ModelAttribute("poll") Poll poll, Model model) {
        Poll newPoll = pollService.createPoll(poll);
        model.addAttribute("poll", newPoll);
        return "redirect:/admin/all";  // Redirect to view all polls after creation
    }

    @GetMapping("/all")
    public String getAllPolls(Model model) {
        List<Poll> polls = pollService.getAllPolls();
        model.addAttribute("polls", polls);
        return "PollList";  // Name of the HTML file to display all polls
    }
}
