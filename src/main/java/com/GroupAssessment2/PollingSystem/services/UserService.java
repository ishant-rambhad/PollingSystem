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

    public UserDetails registerUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }
}
