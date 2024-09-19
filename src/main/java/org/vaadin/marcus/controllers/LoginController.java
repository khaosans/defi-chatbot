package org.vaadin.marcus.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.vaadin.marcus.service.UserService; // Assuming you have a UserService for authentication

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String username, String password) {
        // Implement your authentication logic here
        // For example, check against a database or an in-memory store
        return "admin".equals(username) && "password".equals(password); // Example check
    }

    public void logout() {
        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}