package com.GroupAssessment2.PollingSystem.services;

import com.GroupAssessment2.PollingSystem.models.PollResult;
import com.GroupAssessment2.PollingSystem.repository.PollResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollResultService {

    @Autowired
    private PollResultRepository pollResultRepository;

    public PollResult submitPollResult(PollResult pollResult) {
        return pollResultRepository.save(pollResult);
    }

    public List<PollResult> getAllPollResults() {
        return pollResultRepository.findAll();
    }
}
