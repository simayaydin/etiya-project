package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String temporaryPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Şifrenizi Sıfırlayın");
        message.setText("Geçici şifreniz: " + temporaryPassword +
                "\nLütfen giriş yaptıktan sonra şifrenizi değiştirin.");

        mailSender.send(message);
    }
}
