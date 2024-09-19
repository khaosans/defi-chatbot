package org.vaadin.marcus.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean validateUser(String username, String password) {
        // Implement your user validation logic here
        return "admin".equals(username) && "password".equals(password); // Example
    }
}