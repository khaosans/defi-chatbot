package org.vaadin.marcus.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity; // Import ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/api/logout")
    public ResponseEntity<?> logout() {
        // Clear the security context
        SecurityContextHolder.clearContext();
        
        // Optionally, you can also invalidate the session if using HttpSession
        // HttpSession session = request.getSession(false);
        // if (session != null) {
        //     session.invalidate();
        // }

        return ResponseEntity.ok().build(); // Return a success response
    }

    @GetMapping("/logout")
    public String logout() {
        // Logic to handle logout, e.g., invalidating the session
        return "redirect:/"; // Redirect to the home page or login page after logout
    }
}