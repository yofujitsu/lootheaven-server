package com.yofujitsu.lootheavenserver.Mappers;

import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.entities.dto.LootDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LootMapper {
    LootMapper INSTANCE = Mappers.getMapper(LootMapper.class);

    @Mapping(target = "creatorId", source = "creator.id")
    LootDTO lootToLootDTO(Loot loot);

    @Mapping(target = "creator", ignore = true) // You need to set this later or adjust the logic to fetch the user.
    Loot lootDTOToLoot(LootDTO lootDTO);
}