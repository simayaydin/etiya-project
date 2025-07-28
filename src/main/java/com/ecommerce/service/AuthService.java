package com.ecommerce.service;

import com.ecommerce.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthService {
    ResponseEntity<?> register(User user);
    ResponseEntity<?> login(Map<String, String> loginData);
}
