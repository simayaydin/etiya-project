package com.ecommerce.service;

import com.ecommerce.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminUserService {
    List<User> getAllUsers();
    ResponseEntity<String> deleteUser(Long id);
}
