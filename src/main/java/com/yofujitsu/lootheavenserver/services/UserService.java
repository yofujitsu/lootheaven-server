package com.yofujitsu.lootheavenserver.services;

import com.yofujitsu.lootheavenserver.Mappers.UserMapper;
import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.entities.dto.UserDTO;
import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;
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
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserDTO updateCurrUser(UserDTO updateData) {
        User currentUser = getCurrentUser();
        currentUser.setUsername(updateData.getUsername() != null ? updateData.getUsername() : currentUser.getUsername());
        currentUser.setAvatar(updateData.getAvatar() != null ? updateData.getAvatar() : currentUser.getAvatar());
        userRepository.save(currentUser);
        return userMapper.userToUserDTO(currentUser);
    }

    public UserDTO getUserDTOById(Long userId) {
        User currentUser = getCurrentUser();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getId().equals(currentUser.getId()))
            return userMapper.userToUserDTO(currentUser);
        else
            return userMapper.userToUserDTO(user);
    }

    public UserDTO banUser(Long userId) {
        User currUser = getCurrentUser();
        System.out.println("Curr user claimed");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        System.out.println("Banning user claimed");
        if (currUser.getRole().equals(UserRole.ADMIN)) {
            System.out.println("If statement done");
            user.setActive(false);
            user.setBalance(0L);
        }
        userRepository.save(user);
        System.out.println("saved into repo");

        return userMapper.userToUserDTO(user);
    }

    public UserDTO unbanUser(Long userId) {
        User currUser = getCurrentUser();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        if (currUser.getRole().equals(UserRole.ADMIN)) {
            user.setActive(true);
            user.setBalance(0L);
        }
        userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }

    @Transactional
    public UserDTO updateBalance(Long amountToAdd) {
        User currentUser = getCurrentUser();
        if (amountToAdd < 0) {
            throw new IllegalArgumentException("Amount to add must be non-negative");
        }
        currentUser.setBalance(currentUser.getBalance() + amountToAdd);
        userRepository.save(currentUser);
        return userMapper.userToUserDTO(currentUser);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        System.out.println("Principal type: " + principal.getClass().getName());
        if (authentication == null || !(authentication.getPrincipal() instanceof DefaultOAuth2User)) {
            throw new IllegalStateException("Current user is not authenticated with OAuth2");
        }

        DefaultOAuth2User oidcUser = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oidcUser.getAttributes();
        String discordId = (String) attributes.get("id");
        return userRepository.findByDiscordId(discordId);
    }


    public UserDTO getCurrentUserDTO() {
        User user = getCurrentUser(); // Assume this fetches the current authenticated user
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }
}
