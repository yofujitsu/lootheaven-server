package com.yofujitsu.lootheavenserver.controllers;

import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import com.yofujitsu.lootheavenserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public User getMyInfo() {
        return userService.getCurrentUser();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserInfo(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @PatchMapping("/edit/me")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateCurrUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/balance")
    public ResponseEntity<User> updateBalance(Long amount) {
        try {
            User updatedUser = userService.updateBalance(amount);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
