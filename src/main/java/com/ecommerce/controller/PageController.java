package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling page navigation in the application.
 * <p>
 * This controller manages page routing for:
 * <ul>
 *   <li>Authentication pages (login, forgot password)</li>
 *   <li>Admin dashboard pages</li>
 *   <li>System navigation</li>
 * </ul>
 * 
 * @author simayaydin
 */

@Controller
public class PageController {

    /**
     * Redirects the root URL to the login page.
     * 
     * @return String redirecting to the login page
     */

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    /**
     * Displays the login page.
     * 
     * @return String representing the login view template name
     */

    @GetMapping("/login")
    public String loginPage() {
        return "login"; 
    }

    /**
     * Displays the admin dashboard index page.
     * 
     * @return String representing the admin index view template name
     */

    @GetMapping("/admin/index")
    public String adminIndexPage() {
        return "admin_index"; 
    }

    /**
     * Displays the admin products management page.
     * 
     * @return String representing the admin products view template name
     */

    @GetMapping("/admin/products")
    public String adminProductPage() {
        return "admin_product"; 
    }

    /**
     * Displays the forgot password page.
     * 
     * @return String representing the forgot password view template name
     */

    @GetMapping("/forgot_password")
    public String forgotPasswordPage() {
    return "forgot_password"; 
    }

    /**
     * Displays the admin user management page.
     * 
     * @return String representing the admin users view template name
     */

    @GetMapping("/admin/users")
    public String adminUserPage() {
    return "admin_users";
    }

    /**
     * Displays the admin category management page.
     * 
     * @return String representing the admin category view template name
     */

    @GetMapping("/admin/categories")
    public String showCategoryPage() {
    return "admin_category"; 
    }



}
