package com.UserService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.UserService.model.User;
import com.UserService.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long user_id) {
        return userRepository.findById(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long user_id, User updatedUser) {
        User existingUser = getUserById(user_id);
        existingUser.setPassengerName(updatedUser.getPassengerName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long user_id) {
        User user = getUserById(user_id);
        userRepository.delete(user);
    }
}
