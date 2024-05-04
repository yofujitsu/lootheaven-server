package com.yofujitsu.lootheavenserver.Mappers;

import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.entities.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO userToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setDiscordId(user.getDiscordId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAvatar(user.getAvatar());
        dto.setActive(user.isActive());
        dto.setBalance(user.getBalance());
        dto.setRegDate(user.getRegDate());
        dto.setRole(user.getRole().toString());
        dto.setDealsCount(user.getDealsCount());
        dto.setOrdersCount(user.getOrdersCount());
        return dto;
    }

    public User userDTOToUser(UserDTO dto) {
        User user = new User();
        user.setDiscordId(dto.getDiscordId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setAvatar(dto.getAvatar());
        user.setActive(dto.isActive());
        user.setBalance(dto.getBalance());
        return user;
    }
}