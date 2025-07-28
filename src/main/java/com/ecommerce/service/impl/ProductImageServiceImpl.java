package com.ecommerce.service.impl;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.ProductImage;
import com.ecommerce.repository.ProductImageRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @Override
    public String uploadImage(Long productId, MultipartFile file) {
        System.out.println("UPLOAD STARTED for productId = " + productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("Invalid file type: " + file.getContentType());
        }

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new RuntimeException("File name is null!");
        }

        // Dosya adını güvenli hale getir
        String sanitizedFileName = originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
        String fileName = UUID.randomUUID() + "_" + sanitizedFileName;

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Klasör yoksa oluştur
        }

        File destinationFile = new File(uploadDir, fileName);
        try {
            file.transferTo(destinationFile);
            System.out.println("File saved to: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("File transfer error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to save file", e);
        }

        String imageUrl = "/uploads/" + fileName; // Web'den erişilecek URL

        ProductImage image = ProductImage.builder()
                .product(product)
                .imageUrl(imageUrl)
                .build();

        productImageRepository.save(image);
        System.out.println("Image DB saved for productId = " + productId);

        return imageUrl;
    }
}
