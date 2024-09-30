package com.backend_training.app.services;


import com.backend_training.app.dto.AuthRequest;
import com.backend_training.app.dto.AuthResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.backend_training.app.models.User;
import com.backend_training.app.repositories.UserRepository;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    public ResponseEntity<?> login(AuthRequest authRequest, HttpServletResponse response) {
        User user = userRepository.findByUsername(authRequest.getUsername());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            String jwtToken = jwtService.generateToken(String.valueOf(user.getId()));
            return ResponseEntity.ok(new AuthResponse(jwtToken, null));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, "Invalid credentials"));
    }

    public ResponseEntity<?> register(AuthRequest authRequest, HttpServletResponse response) {
        User existingUser = userRepository.findByUsername(authRequest.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse(null, "Username already exists"));
        }
        User newUser = new User();
        newUser.setUsername(authRequest.getUsername());
        newUser.setEmail(authRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        userRepository.save(newUser);
        String jwtToken = jwtService.generateToken(String.valueOf(newUser.getId()));
        return ResponseEntity.ok(new AuthResponse(jwtToken, null));
    }
}