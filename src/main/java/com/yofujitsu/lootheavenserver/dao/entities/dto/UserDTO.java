package com.yofujitsu.lootheavenserver.dao.entities.dto;

import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;

public record UserDTO(Long id, String email, String username, UserRole role) {
}
