package com.ecommerce.service.impl;

import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.ProductImage;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductImageRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> getAllProductsForAdmin() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> {
            String imageUrl = productImageRepository.findByProductId(product.getId()).stream()
                    .findFirst()
                    .map(ProductImage::getImageUrl)
                    .orElse(null);

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

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        if (request.getCategoryId() != null) {
            categoryRepository.findById(request.getCategoryId())
                    .ifPresent(product::setCategory);
        }

        Product saved = productRepository.save(product);

        return ProductResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .price(saved.getPrice())
                .imageUrl(null)
                .categoryId(saved.getCategory() != null ? saved.getCategory().getId() : null)
                .categoryName(saved.getCategory() != null ? saved.getCategory().getName() : null)
                .build();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
public List<ProductResponse> getAllProductsForUser() {
    List<Product> products = productRepository.findAll();

    return products.stream().map(product -> {
        String imageUrl = productImageRepository.findByProductId(product.getId()).stream()
                .findFirst()
                .map(ProductImage::getImageUrl)
                .orElse(null);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(imageUrl)
                .build();
    }).collect(Collectors.toList());
}

@Override
public Optional<ProductResponse> getProductById(Long id) {
    return productRepository.findById(id).map(product -> {
        String imageUrl = productImageRepository.findByProductId(product.getId()).stream()
                .findFirst()
                .map(ProductImage::getImageUrl)
                .orElse(null);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(imageUrl)
                .build();
    });
}

}
