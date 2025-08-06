package com.ecommerce.controller;

import com.ecommerce.dto.CategoryResponse;
import com.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling category-related operations for regular users.
 * <p>
 * This controller provides endpoints for users to:
 * <ul>
 *   <li>View all available categories</li>
 *   <li>Browse products by category</li>
 * </ul>
 * All endpoints are mapped under "/api/user/categories".
 *
 * @author simayaydin
 */

@RestController
@RequestMapping("/api/user/categories")
@RequiredArgsConstructor
public class CategoryUserController {

    private final CategoryService categoryService;

    /**
     * Retrieves all available categories in the system.
     * <p>
     * This endpoint allows users to view all active categories.
     * Categories are returned in a simplified DTO format for better performance.
     *
     * @return ResponseEntity containing a list of {@link CategoryResponse} objects
     *         representing all available categories
     */

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    
}
