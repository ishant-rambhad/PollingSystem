package com.GroupAssessment2.PollingSystem.services;

import com.GroupAssessment2.PollingSystem.models.Poll;
// import com.GroupAssessment2.PollingSystem.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private com.GroupAssessment2.PollingSystem.repository.PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public Poll getPollById(Long id) {
        return pollRepository.findById(id).orElse(null);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Poll savePoll(Poll poll) {
        return pollRepository.save(poll);
    }
}
