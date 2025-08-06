package com.ecommerce.controller;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.dto.UserResponse;
import com.ecommerce.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AdminUserController is responsible for handling administrative operations
 * related to user management. This includes listing all users and deleting users.
 * <p>
 * This controller is restricted to admin users only and is mapped under "/api/admin".
 *
 * @author simayaydin
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    /**
     * Retrieves all users in the system.
     * <p>
     * This endpoint is typically used by admin users to manage registered users.
     *
     * @return ResponseEntity containing a list of {@link UserResponse}
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(adminUserService.getAllUsers());
    }

    /**
     * Deletes a user by their unique ID.
     * <p>
     * This action is irreversible and should only be performed by an admin.
     *
     * @param id the ID of the user to be deleted
     * @return ResponseEntity containing an {@link ApiResponse} indicating success or failure
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        return adminUserService.deleteUser(id);
    }
}
