package com.yofujitsu.lootheavenserver.dao.entities.dto;

import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;

public record UserDTO(String email, String username, String message) {
}
