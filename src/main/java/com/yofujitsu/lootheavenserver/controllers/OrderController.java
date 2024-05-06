package com.yofujitsu.lootheavenserver.controllers;

import com.yofujitsu.lootheavenserver.dao.entities.dto.OrderDTO;
import com.yofujitsu.lootheavenserver.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{lootId}")
    public ResponseEntity<String> purchaseLoot(@PathVariable String lootId) {
        String content = orderService.purchaseLoot(Long.parseLong(lootId));
        return ResponseEntity.ok(content);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String orderId) {
        OrderDTO orderDTO = orderService.getOrderById(Long.parseLong(orderId));
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/my")
    public ResponseEntity<List<OrderDTO>> getMyOrders() {
        List<OrderDTO> orderDTO = orderService.getMyOrders();
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orderDTO = orderService.getAllOrders();
        return ResponseEntity.ok(orderDTO);
    }
}
