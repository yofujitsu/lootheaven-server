package com.yofujitsu.lootheavenserver.controllers;

import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.entities.dto.UserDTO;
import com.yofujitsu.lootheavenserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/ban/{userId}")
    public ResponseEntity<UserDTO> banUser(@PathVariable String userId) {
        UserDTO user = userService.banUser(Long.parseLong(userId));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/unban/{userId}")
    public ResponseEntity<UserDTO> unbanUser(@PathVariable String userId) {
        UserDTO user = userService.unbanUser(Long.parseLong(userId));
        return ResponseEntity.ok(user);
    }
}
