package org.vaadin.marcus.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.vaadin.marcus.service.UserService; // Assuming you have a UserService for authentication

@Controller
public class LoginController {

    private final Map<String, String> sessionCache = new HashMap<>(); // Simple in-memory cache
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String username, String password) {
        // Implement your authentication logic here
        if (authenticate(username, password)) {
            String sessionId = generateSessionId(username); // Generate a session ID
            sessionCache.put(sessionId, username); // Store session in cache
            return true;
        }
        return false;
    }

    public String getCurrentSessionId() {
        // Return the last session ID (this is a simple example)
        return sessionCache.keySet().stream().findFirst().orElse(null);
    }

    public void logout(String sessionId) {
        sessionCache.remove(sessionId); // Remove session from cache
    }

    private boolean authenticate(String username, String password) {
        // Replace with actual authentication logic
        return "user".equals(username) && "password".equals(password);
    }

    private String generateSessionId(String username) {
        // Generate a unique session ID (this is a simple example)
        return username + "-" + System.currentTimeMillis();
    }

    public String getUserFromSession(String sessionId) {
        return sessionCache.get(sessionId); // Retrieve user from session cache
    }

    public void logout() {
        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}