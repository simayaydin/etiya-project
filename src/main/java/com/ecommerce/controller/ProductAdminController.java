package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product added successfully.");
    }
}
