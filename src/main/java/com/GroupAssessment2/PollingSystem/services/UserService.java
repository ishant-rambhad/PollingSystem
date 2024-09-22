package com.GroupAssessment2.PollingSystem.services;

import com.GroupAssessment2.PollingSystem.models.UserDetails;
import com.GroupAssessment2.PollingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public void registerUser(UserDetails userDetails) {
        if (userRepository.findByEmail(userDetails.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already registered");
        }
        userRepository.save(userDetails);
    }

    // Get all users
    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }

    // Authenticate user by email, password, and name
    public boolean authenticateUser(String email, String password, String name) {
        UserDetails user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password) && user.getName().equals(name)) {
            return true;  // User authenticated successfully
        }
        return false;  // Authentication failed
    }
}
