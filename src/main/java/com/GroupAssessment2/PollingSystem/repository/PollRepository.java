package com.GroupAssessment2.PollingSystem.repository;

import com.GroupAssessment2.PollingSystem.models.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
}
