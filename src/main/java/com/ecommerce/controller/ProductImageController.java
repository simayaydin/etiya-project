package com.ecommerce.controller;

import com.ecommerce.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/product-image")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping("/{productId}")
    public ResponseEntity<String> uploadImage(@PathVariable Long productId,
                                              @RequestParam("file") MultipartFile file) {
        String imageUrl = productImageService.uploadImage(productId, file);
        return ResponseEntity.ok(imageUrl);
    }
}
