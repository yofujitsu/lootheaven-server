package com.yofujitsu.lootheavenserver.dao.entities.dto;

import com.yofujitsu.lootheavenserver.dao.entities.enums.LootType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LootDTO {
    private Long id;
    private String name;
    private String type;
    private String description;
    private Long price;
    private String status;
    private String productName;
    private Long creatorId;
    private LocalDateTime published;
    private String contentUrl;

}
