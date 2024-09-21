package org.sour.service;

import org.springframework.stereotype.Service;
import org.sour.model.User;

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

    public Object authenticate(String anyString, String anyString2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }
}