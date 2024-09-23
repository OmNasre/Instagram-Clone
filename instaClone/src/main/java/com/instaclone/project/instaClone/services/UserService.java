package com.instaclone.project.instaClone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaclone.project.instaClone.Entities.User;
import com.instaclone.project.instaClone.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // A repository to interact with DB

    public boolean isValidUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // New method to get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    // Save a new user
    public void save(User newUser) {
        userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll(); // Fetch all users from the repository
    }

    // Delete user by ID
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId); // Delete user by ID
    }
}
