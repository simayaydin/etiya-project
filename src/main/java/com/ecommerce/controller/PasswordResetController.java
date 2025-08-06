package com.ecommerce.controller;

import com.ecommerce.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling password reset operations.
 * <p>
 * This controller manages the password reset workflow including:
 * <ul>
 *   <li>Initiating password reset requests</li>
 *   <li>Sending reset emails to users</li>
 *   <li>Validating reset tokens</li>
 * </ul>
 * All endpoints are mapped under "/api/auth".
 *
 * @author simayaydin
 */

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    /**
     * Initiates the password reset process for a given email address.
     * <p>
     * This endpoint:
     * <ul>
     *   <li>Validates the email exists in the system</li>
     *   <li>Generates a unique reset token</li>
     *   <li>Sends a password reset email to the user</li>
     * </ul>
     *
     * @param email the email address of the user requesting password reset
     * @return ResponseEntity containing a success message or error details
     * @throws IllegalArgumentException if the email is not found in the system
     */

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        return passwordResetService.resetPasswordByEmail(email);
    }
}