package com.ecommerce.service;

import org.springframework.http.ResponseEntity;

public interface PasswordResetService {
    ResponseEntity<String> resetPasswordByEmail(String email);
}