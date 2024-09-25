package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.PollResult;
import com.GroupAssessment2.PollingSystem.services.PollResultService;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;  

@Controller
@RequestMapping("/pollresults")
public class PollResultController {

    @Autowired
    private PollResultService pollResultService;

    // @PostMapping("/submit")
    // public ResponseEntity<PollResult> submitPollResult(@RequestParam String email,
    //         @RequestParam String selectedOption,
    //         @RequestParam Long pollId) {
    //     PollResult savedResult = pollResultService.submitPollResult(email, selectedOption, pollId);
    //     return ResponseEntity.ok(savedResult);
    // }

    @GetMapping("/all")
    public List<PollResult> getAllPollResults() {
        return pollResultService.getAllPollResults();
    }

//     @GetMapping("/results")
//     public String getPollResults(Model model) {
//         System.out.println("getPollResults called");
//         List<PollResult> pollResults = pollResultService.getAllPollResults();
//         model.addAttribute("pollResults", pollResults);

//         if (pollResults.isEmpty()) { // Check if pollResults is empty
//             System.out.println("No poll results available.");
//             model.addAttribute("error", "No poll results available.");
//             return "Poll_Result"; // Return the view with an error message
//         }

//         try {
//             System.out.println("Getting poll results...");
//             Long pollId = pollResults.get(0).getPoll().getId(); // Assuming the first poll result's poll ID is the one
//                                                                 // we want
//             String pollQuestion = pollResultService.getPollQuestion(pollId);
//             String allPollOptions = pollResultService.getAllPollOptions(pollId);
//             System.out.println(allPollOptions);
//             model.addAttribute("allPollOptions", allPollOptions);
//             model.addAttribute("pollQuestion", pollQuestion);
//             System.out.println("Poll question: " + pollQuestion);
//             int totalVotes = pollResultService.getTotalVotes(pollId);
//             System.out.println("Total votes: " + totalVotes);
//             model.addAttribute("pollQuestion", pollQuestion);
//             model.addAttribute("totalVotes", totalVotes);
//         } catch (Exception e) {
//             System.out.println("An error occurred while retrieving poll results: " + e.getMessage());
//             model.addAttribute("error", "An error occurred while retrieving poll results: " + e.getMessage());
//             return "Poll_Result"; // Return the view with an error message
//         }

//         return "Poll_Result";
//     }
// }
@GetMapping("/results")
public String getPollResults(Model model) {
    List<PollResult> pollResults = pollResultService.getAllPollResults();
    model.addAttribute("pollResults", pollResults);

    if (pollResults.isEmpty()) {
        model.addAttribute("error", "No poll results available.");
        return "Poll_Result";
    }

    try {
        Long pollId = pollResults.get(0).getPoll().getId();
        String pollQuestion = pollResultService.getPollQuestion(pollId);
        String allPollOptions = pollResultService.getAllPollOptions(pollId);
        
        model.addAttribute("pollQuestion", pollQuestion);
        model.addAttribute("allPollOptions", allPollOptions);  // Pass the options string
        int totalVotes = pollResultService.getTotalVotes(pollId);
        model.addAttribute("totalVotes", totalVotes);

    } catch (Exception e) {
        model.addAttribute("error", "An error occurred while retrieving poll results: " + e.getMessage());
        return "Poll_Result";
    }

    return "Poll_Result";
}

}
