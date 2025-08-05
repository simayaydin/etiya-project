package com.ecommerce.controller;

import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.AuthResponse;
import com.ecommerce.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @Operation(
    summary = "Register new user",
    description = "Registers a new user with given username, email, and password."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "User registered successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input or user already exists"),
    @ApiResponse(responseCode = "500", description = "Server error during registration")
})

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    @Operation(
    summary = "Login",
    description = "Authenticate user with username and password. Returns JWT token on success."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned"),
    @ApiResponse(responseCode = "401", description = "Invalid username or password"),
    @ApiResponse(responseCode = "403", description = "Access denied due to invalid role or filter")
})

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
