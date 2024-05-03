package com.yofujitsu.lootheavenserver.dao.entities.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;


@Data
public class UserDTO {
    private Long id;
    private String discordId;
    private String username;
    private String email;
    private String role;
    private LocalDate regDate;
    private Long dealsCount;
    private Long ordersCount;
    private Long balance;
    private String avatar;
    private boolean active;
}
