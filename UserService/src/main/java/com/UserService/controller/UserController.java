package com.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.UserService.model.User;
import com.UserService.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable Long user_id) {
        return ResponseEntity.ok(userService.getUserById(user_id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable Long user_id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user_id, user));
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }
}
