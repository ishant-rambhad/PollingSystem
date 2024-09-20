package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.Poll;
import com.GroupAssessment2.PollingSystem.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping("/create")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll newPoll = pollService.createPoll(poll);
        return ResponseEntity.ok(newPoll);
    }

    @GetMapping("/all")
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }
}
