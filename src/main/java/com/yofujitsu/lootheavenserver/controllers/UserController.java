package com.yofujitsu.lootheavenserver.controllers;

import com.yofujitsu.lootheavenserver.dao.entities.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        // This endpoint could instruct where the client should redirect for OAuth2 login
        return ResponseEntity.ok("Redirect to OAuth2 login provider.");
    }

    // Endpoint to retrieve currently logged-in user details
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal OidcUser principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDTO(null, null, "No user is currently logged in."));
        }
        String name = principal.getName();
        String email = principal.getEmail();
        return ResponseEntity.ok(new UserDTO(email, name, "User details fetched successfully."));
    }
}
