package com.yofujitsu.lootheavenserver.services;

import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateCurrUser(User updateData) {
        User user = getCurrentUser();
        user.setUsername(updateData.getUsername() != null ? updateData.getUsername() : user.getUsername());
        user.setAvatar(updateData.getAvatar() != null ? updateData.getAvatar() : user.getAvatar());
        return userRepository.save(user);
    }

    public User banUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        user.setActive(!user.isActive());
        user.setBalance(0L);
        return userRepository.save(user);
    }

    @Transactional
    public User updateBalance(Long amountToAdd) {
        User user = getCurrentUser();
        if (amountToAdd < 0) {
            throw new IllegalArgumentException("Amount to add must be non-negative");
        }
        user.setBalance(user.getBalance() + amountToAdd);
        return userRepository.save(user);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User oidcUser = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oidcUser.getAttributes();
        String discordId = (String) attributes.get("id");
        return userRepository.findByDiscordId(discordId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
