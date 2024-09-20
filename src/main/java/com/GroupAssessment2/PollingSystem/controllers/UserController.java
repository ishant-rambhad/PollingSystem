package com.GroupAssessment2.PollingSystem.controllers;

import com.GroupAssessment2.PollingSystem.models.UserDetails;
import com.GroupAssessment2.PollingSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDetails> registerUser(@RequestBody UserDetails userDetails) {
        UserDetails newUser = userService.registerUser(userDetails);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/all")
    public List<UserDetails> getAllUsers() {
        return userService.getAllUsers();
    }
}
