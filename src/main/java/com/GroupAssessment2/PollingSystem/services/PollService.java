package com.GroupAssessment2.PollingSystem.services;

import com.GroupAssessment2.PollingSystem.models.Poll;
import com.GroupAssessment2.PollingSystem.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }
}
