package com.yofujitsu.lootheavenserver.controllers;

import com.yofujitsu.lootheavenserver.dao.entities.dto.UserDTO;
import com.yofujitsu.lootheavenserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyInfo() {
        UserDTO userDTO = userService.getCurrentUserDTO();
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable String userId) {
        UserDTO userDTO = userService.getUserDTOById(Long.parseLong(userId));
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/edit/me")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUserDTO = userService.updateCurrUser(userDTO);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @PatchMapping("/balance")
    public ResponseEntity<UserDTO> updateBalance(@RequestParam Long amount) {
        try {
            UserDTO updatedUserDTO = userService.updateBalance(amount);
            return ResponseEntity.ok(updatedUserDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

