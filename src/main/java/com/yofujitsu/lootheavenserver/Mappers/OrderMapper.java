package com.yofujitsu.lootheavenserver.Mappers;

import com.yofujitsu.lootheavenserver.dao.entities.Order;
import com.yofujitsu.lootheavenserver.dao.entities.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO orderToOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setBuyerId(order.getBuyer() != null ? order.getBuyer().getId() : null);
        dto.setSellerId(order.getSeller() != null ? order.getSeller().getId() : null);
        dto.setLootId(order.getLoot() != null ? order.getLoot().getId() : null);
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setCost(order.getCost());
        return dto;
    }

    public Order orderDTOToOrder(OrderDTO dto) {
        Order order = new Order();
        // Similar to Loot, setting of relational entities like buyer, seller, and loot should be handled separately
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        order.setCost(dto.getCost());
        return order;
    }
}