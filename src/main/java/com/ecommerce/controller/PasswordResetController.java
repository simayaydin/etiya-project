package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.entity.UserLog;
import com.ecommerce.repository.UserLogRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class PasswordResetController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LogManager.getLogger(PasswordResetController.class);


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            logger.warn("Password reset requested for non-existing user: {}", email);
            return ResponseEntity.badRequest().body("No user found with this email.");
        }

        User user = userOpt.get();

        // Generate 8-character temporary password
        String temporaryPassword = UUID.randomUUID().toString().substring(0, 8);

        // Update and save encoded password
        user.setPassword(passwordEncoder.encode(temporaryPassword));
        userRepository.save(user);

        // Send email
        emailService.sendPasswordResetEmail(user.getEmail(), temporaryPassword);

        // Log to DB
        UserLog log = new UserLog(user.getUsername(), user.getEmail(), "PASSWORD_RESET", LocalDateTime.now());
        userLogRepository.save(log);

        // Log to log4j
        logger.info("Temporary password sent to user: {}", email);

        return ResponseEntity.ok("A temporary password has been sent to your email.");

        
    }
}
