package com.GroupAssessment2.PollingSystem.repository;

import com.GroupAssessment2.PollingSystem.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    // Find user by email
    UserDetails findByEmail(String email);
}
