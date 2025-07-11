package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("This user is already used.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); 
        userRepository.save(user);
        return ResponseEntity.ok("Registration Successful!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
    String username = loginData.get("username");
    String password = loginData.get("password");

    System.out.println(">> Login endpoint hit by: " + username);

    try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}

}
