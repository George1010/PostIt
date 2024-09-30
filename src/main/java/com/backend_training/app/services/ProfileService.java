package com.backend_training.app.services;

import com.backend_training.app.models.User;
import com.backend_training.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> getProfile(String userID) {
        return ResponseEntity.ok(userRepository.findById(userID).orElseThrow());
    }

    public ResponseEntity<User> createProfile(User profile) {
        userRepository.save(profile);
        return ResponseEntity.status(201).body(profile);
    }

    public ResponseEntity<User> updateProfile(User profile) {
        return ResponseEntity.ok(userRepository.save(profile));
    }
}