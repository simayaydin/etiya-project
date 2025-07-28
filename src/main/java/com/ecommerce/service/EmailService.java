package com.ecommerce.service;

public interface EmailService {
    void sendPasswordResetEmail(String to, String temporaryPassword);
}
