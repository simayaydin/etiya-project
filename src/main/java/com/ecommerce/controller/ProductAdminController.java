package com.ecommerce.controller;

import com.ecommerce.dto.ProductResponse;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.ProductImage;
import com.ecommerce.repository.ProductImageRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductService productService;

    // GET /api/admin/products
   @GetMapping
public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();

    return products.stream().map(product -> {
        List<ProductImage> images = productImageRepository.findByProductId(product.getId());
        String imageUrl = images.isEmpty() ? null : images.get(0).getImageUrl();

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(imageUrl)
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .build();
    }).collect(Collectors.toList());
}

    // POST /api/admin/products/add
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);

        ProductResponse response = ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .imageUrl(null) 
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
