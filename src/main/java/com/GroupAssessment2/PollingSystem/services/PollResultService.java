package com.GroupAssessment2.PollingSystem.services;

import com.GroupAssessment2.PollingSystem.models.Poll;
import com.GroupAssessment2.PollingSystem.models.PollResult;
import com.GroupAssessment2.PollingSystem.repository.PollRepository;
import com.GroupAssessment2.PollingSystem.repository.PollResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollResultService {

    @Autowired
    private PollResultRepository pollResultRepository;

    @Autowired
    private PollRepository pollRepository;

    public PollResult submitPollResult(String email, String selectedOption, Long pollId) {
        // Check if the user has already voted
        Optional<PollResult> existingPollResult = pollResultRepository.findByEmailAndPollId(email, pollId);

        if (existingPollResult.isPresent()) {
            throw new IllegalStateException("User has already voted.");
        }

        // Fetch the poll by pollId
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new IllegalArgumentException("Poll not found"));

        // Create a new PollResult
        PollResult pollResult = new PollResult();
        pollResult.setPoll(poll); // Set the poll
        pollResult.setEmail(email);
        pollResult.setTitle(poll.getTitle());
        pollResult.setQuestion(poll.getQuestion());
        pollResult.setUsername(email); // Assuming username is the same as email for simplicity

        // Set the options
        pollResult.setOption1(poll.getOption1());

        pollResult.setOption2(poll.getOption2());
        pollResult.setOption3(poll.getOption3());
        pollResult.setOption4(poll.getOption4());
        pollResult.setOption5(poll.getOption5());
        pollResult.setOption6(poll.getOption6());
        pollResult.setOption7(poll.getOption7());
        pollResult.setOption8(poll.getOption8());
        pollResult.setOption9(poll.getOption9());
        pollResult.setOption10(poll.getOption10());
        System.out.println();
        System.out.println(pollResult.getOption2());
        System.out.println(pollResult.getOption3());
        System.out.println(pollResult.getOption4());
        System.out.println(pollResult.getOption5());
        System.out.println(pollResult.getOption6());
        System.out.println(pollResult.getOption7());
        System.out.println(pollResult.getOption8());
        System.out.println(pollResult.getOption9());
        System.out.println(pollResult.getOption10());

        System.out.println(selectedOption);
        // Increment the count based on the selected option
        if (selectedOption.equals(pollResult.getOption1())) {
            pollResult.setCountoption1(pollResult.getCountoption1() + 1);
        } else if (selectedOption.equals(pollResult.getOption2())) {
            pollResult.setCountoption2(pollResult.getCountoption2() + 1);
        } else if (selectedOption.equals(pollResult.getOption3())) {
            pollResult.setCountoption3(pollResult.getCountoption3() + 1);
        } else if (selectedOption.equals(pollResult.getOption4())) {
            pollResult.setCountoption4(pollResult.getCountoption4() + 1);
        } else if (selectedOption.equals(pollResult.getOption5())) {
            pollResult.setCountoption5(pollResult.getCountoption5() + 1);
        } else if (selectedOption.equals(pollResult.getOption6())) {
            pollResult.setCountoption6(pollResult.getCountoption6() + 1);
        } else if (selectedOption.equals(pollResult.getOption7())) {
            pollResult.setCountoption7(pollResult.getCountoption7() + 1);
        } else if (selectedOption.equals(pollResult.getOption8())) {
            pollResult.setCountoption8(pollResult.getCountoption8() + 1);
        } else if (selectedOption.equals(pollResult.getOption9())) {
            pollResult.setCountoption9(pollResult.getCountoption9() + 1);
        } else if (selectedOption.equals(pollResult.getOption10())) {
            pollResult.setCountoption10(pollResult.getCountoption10() + 1);
        } else {
            throw new IllegalArgumentException("Invalid option selected");
        }

        // Save the new poll result and return it
        return pollResultRepository.save(pollResult);
    }

    public List<PollResult> getAllPollResults() {
        return pollResultRepository.findAll();
    }

    public String getPollQuestion(Long pollId) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new IllegalArgumentException("Poll not found"));
        return poll.getQuestion();
    }

    public String getAllPollOptions(Long pollId) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new IllegalArgumentException("Poll not found"));
        StringBuilder allOptions = new StringBuilder();
        if (poll.getOption1() != null) {
            allOptions.append(poll.getOption1()).append(", ");
        }
        if (poll.getOption2() != null) {
            allOptions.append(poll.getOption2()).append(", ");
        }
        if (poll.getOption3() != null) {
            allOptions.append(poll.getOption3()).append(", ");
        }
        if (poll.getOption4() != null) {
            allOptions.append(poll.getOption4()).append(", ");
        }
        if (poll.getOption5() != null) {
            allOptions.append(poll.getOption5()).append(", ");
        }
        if (poll.getOption6() != null) {
            allOptions.append(poll.getOption6()).append(", ");
        }
        if (poll.getOption7() != null) {
            allOptions.append(poll.getOption7()).append(", ");
        }
        if (poll.getOption8() != null) {
            allOptions.append(poll.getOption8()).append(", ");
        }
        if (poll.getOption9() != null) {
            allOptions.append(poll.getOption9()).append(", ");
        }
        if (poll.getOption10() != null) {
            allOptions.append(poll.getOption10());
        }
        // Remove the last ", " if present
        if (allOptions.length() > 2) {
            allOptions.delete(allOptions.length() - 2, allOptions.length());
        }
        return allOptions.toString();
    }

    public int getTotalVotes(Long pollId) {
        List<PollResult> pollResults = pollResultRepository.findAll();
        int totalVotes = 0;
        for (PollResult pollResult : pollResults) {
            if (pollResult.getId().equals(pollId)) {
                totalVotes += pollResult.getCountoption1() + pollResult.getCountoption2() + pollResult.getCountoption3()
                        +
                        pollResult.getCountoption4() + pollResult.getCountoption5() + pollResult.getCountoption6() +
                        pollResult.getCountoption7() + pollResult.getCountoption8() + pollResult.getCountoption9() +
                        pollResult.getCountoption10();
            }
        }
        System.out.println(totalVotes);
        return totalVotes;
    }
}