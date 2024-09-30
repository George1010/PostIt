package com.backend_training.app.controllers;

import com.backend_training.app.models.User;
import com.backend_training.app.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resources/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{userID}")
    public ResponseEntity<User> getProfile(@PathVariable String userID) {
        return profileService.getProfile(userID);
    }

    @PostMapping
    public ResponseEntity<User> createProfile(@RequestBody User profile) {
        return profileService.createProfile(profile);
    }

    @PutMapping
    public ResponseEntity<User> updateProfile(@RequestBody User profile) {
        return profileService.updateProfile(profile);
    }
}
