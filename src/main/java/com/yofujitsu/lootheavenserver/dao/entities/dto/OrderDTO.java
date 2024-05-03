package com.yofujitsu.lootheavenserver.dao.entities.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private Long buyerId;
    private Long sellerId;
    private LocalDateTime orderDate;
    private String status;
    private Long lootId;
    private Long cost;

}