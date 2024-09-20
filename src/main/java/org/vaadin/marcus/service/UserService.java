package org.vaadin.marcus.service;

import org.springframework.stereotype.Service;
import org.vaadin.marcus.model.User;

@Service
public class UserService {
    public boolean validateUser(String username, String password) {
        // Implement your user validation logic here
        return "admin".equals(username) && "password".equals(password); // Example
    }

    public void createUser(String username, String password) {
        User newUser = new User(username, password);
        // Here you would typically interact with your database to store the user
        // For example, using a UserRepository to save the user:
        // userRepository.save(newUser);
        System.out.println("User created with username: " + newUser.getUsername());
    }
}