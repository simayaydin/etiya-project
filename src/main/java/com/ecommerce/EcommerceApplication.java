package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecommerce.config.DotenvLoader;

@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        DotenvLoader.loadEnv();
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
