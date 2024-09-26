package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.PollResult;
import com.GroupAssessment2.PollingSystem.services.PollResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;

@Controller
@RequestMapping("/pollresults")
public class PollResultController {

    @Autowired
    private PollResultService pollResultService;


    @GetMapping("/all")
    public List<PollResult> getAllPollResults() {
        return pollResultService.getAllPollResults();
    }

    @GetMapping("/results")
public String getPollResults(Model model) {
    List<PollResult> pollResults = pollResultService.getAllPollResults();

    if (pollResults.isEmpty()) {
        model.addAttribute("error", "No poll results available.");
        return "Poll_Result";
    }

    try {
        Long pollId = pollResults.get(0).getPoll().getId(); // Ensure Poll class has getId() method
        String pollQuestion = pollResultService.getPollQuestion(pollId);
        String allPollOptions = pollResultService.getAllPollOptions(pollId);
        
        // Split options into a list
        List<String> pollOptionsList = Arrays.asList(allPollOptions.split(", "));

        // Get total votes using the new method in the service
        int totalVotes = pollResultService.getTotalVotes(pollId);

        // Prepare the list of formatted results with votes and percentages
        List<Map<String, Object>> formattedResults = new ArrayList<>();
        for (PollResult result : pollResults) {
            Map<String, Object> resultData = new HashMap<>();

            // You can adjust this to match however you're storing options in the PollResult class
            if (result.getOption1() != null) {
                resultData.put("optionName", result.getOption1());
                resultData.put("votes", result.getCountoption1());
                double percentage = totalVotes > 0 ? (result.getCountoption1() / (double) totalVotes) * 100 : 0.0;
                resultData.put("percentage", String.format("%.2f", percentage));
                formattedResults.add(resultData);
            }
            if (result.getOption2() != null) {
                resultData = new HashMap<>(); // Create new map for each option
                resultData.put("optionName", result.getOption2());
                resultData.put("votes", result.getCountoption2());
                double percentage = totalVotes > 0 ? (result.getCountoption2() / (double) totalVotes) * 100 : 0.0;
                resultData.put("percentage", String.format("%.2f", percentage));
                formattedResults.add(resultData);
            }

            // Repeat for option3 to option10 if necessary
        }

        // Pass everything to the model
        model.addAttribute("pollQuestion", pollQuestion);
        model.addAttribute("pollOptionsList", pollOptionsList); // Poll options list
        model.addAttribute("totalVotes", totalVotes);
        model.addAttribute("formattedResults", formattedResults); // Now passing formatted results
        model.addAttribute("pollResults", pollResults); // Add the poll data to the model

    } catch (Exception e) {
        System.out.println("An error occurred while retrieving poll results: " + e.getMessage());
        e.printStackTrace();
        model.addAttribute("error", "An error occurred while retrieving poll results: " + e.getMessage());
        return "Poll_Result";
    }

    return "Poll_Result";
}


}
