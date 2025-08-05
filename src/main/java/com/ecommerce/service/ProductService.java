package com.ecommerce.service;

import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> getAllProductsForAdmin();
    ProductResponse createProduct(ProductRequest request);
    void deleteProduct(Long id);
    List<ProductResponse> getAllProductsForUser();
    Optional<ProductResponse> getProductById(Long id);
}
