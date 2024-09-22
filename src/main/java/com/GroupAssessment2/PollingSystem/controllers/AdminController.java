package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.AdminCred;
import com.GroupAssessment2.PollingSystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Render login page when accessed via GET
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("adminCred", new AdminCred()); // Add an empty AdminCred object to the model
        return "Admin_Login"; // Return the login HTML page
    }

    @PostMapping("/login")
    public String authenticateAdmin(@ModelAttribute("adminCred") AdminCred adminCred, Model model) {
        System.out.println("Email: " + adminCred.getEmail()); // Debugging
        System.out.println("Password: " + adminCred.getPassword()); 
        System.out.println("Password: " + adminCred.getUsername()); 

        boolean isAuthenticated = adminService.authenticateAdmin(adminCred.getEmail(), adminCred.getPassword(),adminCred.getUsername());  // Use email instead of username
        if (isAuthenticated) {
            return "redirect:/admin/dashboard"; // Redirect to the dashboard if login is successful
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "Admin_Login"; // Re-render login page with error
        }
    }
    

    // Dashboard after login success
    @GetMapping("/dashboard")
    public String showAdminDashboard() {
        return "admin_dashboard"; // This would be your dashboard HTML page
    }
}
