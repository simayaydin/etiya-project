package com.ecommerce.controller;

import com.ecommerce.dto.CategoryRequest;
import com.ecommerce.dto.CategoryResponse;
import com.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing category operations in admin panel.
 * <p>
 * This controller handles all administrative operations related to categories including:
 * <ul>
 *   <li>Creating new categories</li>
 *   <li>Retrieving all categories</li>
 *   <li>Deleting existing categories</li>
 * </ul>
 * All endpoints are mapped under "/api/admin/categories" and require admin privileges.
 *
 * @author simayaydin
 */

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryService categoryService;

    /**
     * Retrieves all categories available in the system.
     *
     * @return ResponseEntity containing a list of CategoryResponse objects
     */

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /**
     * Creates a new category in the system.
     *
     * @param request the {@link CategoryRequest} containing category details
     * @return ResponseEntity containing the created {@link CategoryResponse}
     * @throws IllegalArgumentException if category with same name already exists
     */

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

     /**
     * Deletes a category by its ID.
     * <p>
     * This operation is irreversible and will remove the category permanently.
     *
     * @param id the ID of the category to delete
     * @return ResponseEntity with no content if deletion is successful

     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
