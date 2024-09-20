package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.AdminCred;
import com.GroupAssessment2.PollingSystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<AdminCred> registerAdmin(@RequestBody AdminCred adminCred) {
        AdminCred newAdmin = adminService.registerAdmin(adminCred);
        return ResponseEntity.ok(newAdmin);
    }

    @GetMapping("/all")
    public List<AdminCred> getAllAdmins() {
        return adminService.getAllAdmins();
    }
}
