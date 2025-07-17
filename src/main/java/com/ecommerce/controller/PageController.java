package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; 
    }

    @GetMapping("/admin/index")
    public String adminIndexPage() {
        return "admin_index"; 
    }

    @GetMapping("/admin/products")
    public String adminProductPage() {
        return "admin_product"; 
    }
    @GetMapping("/forgot_password")
    public String forgotPasswordPage() {
    return "forgot_password"; 
    }

    @GetMapping("/admin/users")
    public String adminUserPage() {
    return "admin_users";
    }


}
