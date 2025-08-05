package com.ecommerce.service;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.dto.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminUserService {

    List<UserResponse> getAllUsers();

    ResponseEntity<ApiResponse> deleteUser(Long id);
}
