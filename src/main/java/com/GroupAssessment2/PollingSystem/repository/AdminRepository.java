package com.GroupAssessment2.PollingSystem.repository;

import com.GroupAssessment2.PollingSystem.models.AdminCred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminCred, Long> {
}
