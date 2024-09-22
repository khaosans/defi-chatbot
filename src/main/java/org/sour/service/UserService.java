package org.sour.service;

import java.util.ArrayList;
import java.util.List;

import org.sour.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>(); // In-memory storage for users

    public boolean validateUser(String username, String password) {
        return "admin".equals(username) && "password".equals(password); // Example validation
    }

    public void createUser(String username, String password) {
        User newUser = new User(username, password);
        users.add(newUser); // Save user to in-memory storage
        System.out.println("User created with username: " + newUser.getUsername());
    }

    public List<User> getAllUsers() {
        return users; // Return the list of users
    }
}