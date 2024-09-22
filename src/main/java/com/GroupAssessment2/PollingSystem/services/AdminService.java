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

    // Register a new admin
    public AdminCred registerAdmin(AdminCred adminCred) {
        return adminRepository.save(adminCred);
    }

    public boolean authenticateAdmin(String email, String password,String username) {
        AdminCred admin = adminRepository.findByEmail(email);  // Use email instead of username
        if (admin != null && admin.getPassword().equals(password) && admin.getUsername().equals(username)) {
            return true;
        }
        return false;
    }
    

    // Get all admins
    public List<AdminCred> getAllAdmins() {
        return adminRepository.findAll();
    }
}
