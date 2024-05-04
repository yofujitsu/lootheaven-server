package com.yofujitsu.lootheavenserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private OAuth2AuthorizedClientService clientService;

    @GetMapping("/logout")
    public ResponseEntity<?> logout(OAuth2AuthenticationToken authentication) {
        if (authentication != null) {
            clientService.removeAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
        }
        return ResponseEntity.ok().body("Logged out successfully");
    }
}
