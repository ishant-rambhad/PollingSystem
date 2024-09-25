package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.PollResult;
import com.GroupAssessment2.PollingSystem.services.PollResultService;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/pollresults")
public class PollResultController {

    @Autowired
    private PollResultService pollResultService;

    // @PostMapping("/submit")
    // public ResponseEntity<PollResult> submitPollResult(@RequestParam String
    // email,
    // @RequestParam String selectedOption,
    // @RequestParam Long pollId) {
    // PollResult savedResult = pollResultService.submitPollResult(email,
    // selectedOption, pollId);
    // return ResponseEntity.ok(savedResult);
    // }

    @GetMapping("/all")
    public List<PollResult> getAllPollResults() {
        return pollResultService.getAllPollResults();
    }


    // @GetMapping("/results")
    // public String getPollResults(Model model) {
    //     List<PollResult> pollResults = pollResultService.getAllPollResults();
    //     model.addAttribute("pollResults", pollResults);

    //     if (pollResults.isEmpty()) {
    //         model.addAttribute("error", "No poll results available.");
    //         return "Poll_Result";
    //     }

    //     try {
    //         Long pollId = pollResults.get(0).getPoll().getId();
    //         System.out.println(pollId);
    //         String pollQuestion = pollResultService.getPollQuestion(pollId);
    //         System.out.println(pollQuestion);
    //         String allPollOptions = pollResultService.getAllPollOptions(pollId);
    //         System.out.println(allPollOptions);
    //         // Split the options in the backend and pass it as a list to the model
    //         List<String> pollOptionsList = Arrays.asList(allPollOptions.split(", "));
    //         System.out.println(pollOptionsList);
    //         model.addAttribute("pollResults", pollResults);
    //         model.addAttribute("pollQuestion", pollQuestion);
    //         model.addAttribute("pollOptionsList", pollOptionsList); // Pass as a list
    //         int totalVotes = pollResultService.getTotalVotes(pollId);
    //         System.out.println(totalVotes);
    //         model.addAttribute("totalVotes", totalVotes);

    //     } catch (Exception e) {
    //         System.out.println("An error occurred while retrieving poll results: " + e.getMessage());
    //         model.addAttribute("error", "An error occurred while retrieving poll results: " + e.getMessage());
    //         return "Poll_Result";
    //     }

    //     return "Poll_Result";
    // }


//     @GetMapping("/results")
// public String getPollResults(Model model) {
//     List<PollResult> pollResults = pollResultService.getAllPollResults();
    
//     if (pollResults == null || pollResults.isEmpty()) {
//         model.addAttribute("error", "No poll results available.");
//         return "Poll_Result";
//     }

//     try {
//         Long pollId = pollResults.get(0).getPoll().getId();
//         String pollQuestion = pollResultService.getPollQuestion(pollId);
//         int totalVotes = pollResultService.getTotalVotes(pollId);

//         List<String> formattedResults = new ArrayList<>();
//         for (PollResult result : pollResults) {
//             String formattedString = formatVotesAndPercentage(result.getVotes(), totalVotes);
//             formattedResults.add(formattedString);
//         }
        
//         model.addAttribute("pollQuestion", pollQuestion);
//         model.addAttribute("formattedResults", formattedResults);
//         model.addAttribute("totalVotes", totalVotes);

//     } catch (Exception e) {
//         e.printStackTrace();
//         model.addAttribute("error", "An error occurred while retrieving poll results: " + e.getMessage());
//         return "Poll_Result";
//     }

//     return "Poll_Result";
// }

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
        
        // Get total votes using the new method in the service
        int totalVotes = pollResultService.getTotalVotes(pollId);
        
        // Prepare the list of formatted results with votes and percentages
        List<String> formattedResults = new ArrayList<>();
        for (PollResult result : pollResults) {
            int votes = result.getTotalVotes();
            double percentage = totalVotes > 0 ? (votes / (double) totalVotes) * 100 : 0.0;
            String formattedString = votes + " votes (" + String.format("%.2f", percentage) + "%)";
            formattedResults.add(formattedString);
        }
        
        model.addAttribute("pollQuestion", pollQuestion);
        model.addAttribute("formattedResults", formattedResults);
        model.addAttribute("totalVotes", totalVotes);
        model.addAttribute("results", pollResults);

    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("error", "An error occurred while retrieving poll results: " + e.getMessage());
        return "Poll_Result";
    }

    return "Poll_Result";
}


}
