package com.ecommerce.service.impl;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.security.JwtUtil;
import com.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    @Override
    public ResponseEntity<?> register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("This user is already used.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return ResponseEntity.ok("Registration Successful!");
    }

    @Override
    public ResponseEntity<?> login(Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "role", "ROLE_" + user.getRole().toUpperCase()
        ));
    }
}
