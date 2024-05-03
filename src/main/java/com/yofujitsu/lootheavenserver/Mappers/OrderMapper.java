package com.yofujitsu.lootheavenserver.Mappers;

import com.yofujitsu.lootheavenserver.dao.entities.Order;
import com.yofujitsu.lootheavenserver.dao.entities.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, LootMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "buyerId", source = "buyer.id")
    @Mapping(target = "sellerId", source = "seller.id")
    @Mapping(target = "lootId", source = "loot.id")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "loot", ignore = true)
    Order orderDTOToOrder(OrderDTO orderDTO);
}
