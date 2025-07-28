package com.ecommerce.service.impl;

import com.ecommerce.entity.User;
import com.ecommerce.entity.UserLog;
import com.ecommerce.repository.UserLogRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.EmailService;
import com.ecommerce.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepository;
    private final UserLogRepository userLogRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LogManager.getLogger(PasswordResetServiceImpl.class);

    @Override
    public ResponseEntity<String> resetPasswordByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            logger.warn("Password reset requested for non-existing user: {}", email);
            return ResponseEntity.badRequest().body("No user found with this email.");
        }

        User user = userOpt.get();
        String temporaryPassword = UUID.randomUUID().toString().substring(0, 8);
        user.setPassword(passwordEncoder.encode(temporaryPassword));
        userRepository.save(user);

        emailService.sendPasswordResetEmail(user.getEmail(), temporaryPassword);

        UserLog log = new UserLog(user.getUsername(), user.getEmail(), "PASSWORD_RESET", LocalDateTime.now());
        userLogRepository.save(log);

        logger.info("Temporary password sent to user: {}", email);
        return ResponseEntity.ok("A temporary password has been sent to your email.");
    }
}
