package com.GroupAssessment2.PollingSystem.services;

import com.GroupAssessment2.PollingSystem.models.AdminCred;
import com.GroupAssessment2.PollingSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminCred registerAdmin(AdminCred adminCred) {
        return adminRepository.save(adminCred);
    }

    public List<AdminCred> getAllAdmins() {
        return adminRepository.findAll();
    }
}
