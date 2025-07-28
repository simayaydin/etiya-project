package com.ecommerce.controller;

import com.ecommerce.dto.ProductResponse;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.ProductImage;
import com.ecommerce.repository.ProductImageRepository;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/products") // Bu controller sadece kullanıcıya özel
@RequiredArgsConstructor
public class ProductUserController {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    // Tüm ürünleri listeler (ana sayfada kullanılır)
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productRepository.findAll();

        // Her Product nesnesini, görseliyle birlikte DTO'ya çeviriyoruz
        List<ProductResponse> responseList = products.stream().map(product -> {
            List<ProductImage> images = productImageRepository.findByProductId(product.getId());
            String imageUrl = images.isEmpty() ? null : images.get(0).getImageUrl(); 

            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .imageUrl(imageUrl)
                    .build();
        }).toList();

        return ResponseEntity.ok(responseList); // DTO listesi frontend'e gider
    }

    // Tek bir ürünü getirir (ürün detay sayfası için)
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    List<ProductImage> images = productImageRepository.findByProductId(product.getId());
                    String imageUrl = images.isEmpty() ? null : images.get(0).getImageUrl();

                    ProductResponse response = ProductResponse.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .imageUrl(imageUrl)
                            .build();

                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build()); 
    }
}
