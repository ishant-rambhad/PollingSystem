package com.GroupAssessment2.PollingSystem.repository;

import com.GroupAssessment2.PollingSystem.models.PollResult;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollResultRepository extends JpaRepository<PollResult, Long> {
    Optional<PollResult> findByEmailAndPollId(String email, Long pollId);
    // List<PollResult> findByUserEmail(String email); // Custom query method to find poll results by user email

}
