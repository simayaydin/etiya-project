package com.ecommerce.service.impl;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.dto.UserResponse;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<ApiResponse> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(404)
                    .body(new ApiResponse("User not found", false));
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully", true));
    }
}
