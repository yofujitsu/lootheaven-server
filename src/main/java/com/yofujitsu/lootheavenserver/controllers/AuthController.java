package com.yofujitsu.lootheavenserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private OAuth2AuthorizedClientService clientService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(OAuth2AuthenticationToken authentication) {
        if (authentication != null) {
            clientService.removeAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
        }
        return ResponseEntity.ok("Successfully logout!");
    }

    @GetMapping("/auth/status")
    public Map<String, Object> authStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal());

        return Collections.singletonMap("isAuthenticated", isAuthenticated);
    }
}
