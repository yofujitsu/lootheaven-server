package com.yofujitsu.lootheavenserver.Mappers;

import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.entities.dto.LootDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class LootMapper {

    public static LootDTO lootToLootDTO(Loot loot) {
        LootDTO dto = new LootDTO();
        dto.setId(loot.getId());
        dto.setName(loot.getName());
        dto.setType(loot.getType());
        dto.setDescription(loot.getDescription());
        dto.setPrice(loot.getPrice());
        dto.setStatus(loot.getStatus());
        dto.setProductName(loot.getProductName());
        dto.setCreatorId(loot.getCreator() != null ? loot.getCreator().getId() : null);
        dto.setPublished(loot.getPublished());
        dto.setContentUrl(loot.getContentUrl());
        return dto;
    }

    public static Loot lootDTOToLoot(LootDTO dto) {
        Loot loot = new Loot();
        // This method typically won't set the creator directly as it's done within the service layer
        loot.setName(dto.getName());
        loot.setDescription(dto.getDescription());
        loot.setPrice(dto.getPrice());
        loot.setStatus(dto.getStatus());
        loot.setProductName(dto.getProductName());
        loot.setPublished(dto.getPublished());
        loot.setContentUrl(dto.getContentUrl());
        return loot;
    }
}