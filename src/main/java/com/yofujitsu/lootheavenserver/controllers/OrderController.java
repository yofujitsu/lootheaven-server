package com.yofujitsu.lootheavenserver.controllers;

import com.yofujitsu.lootheavenserver.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{lootId}")
    public ResponseEntity<String> purchaseLoot(@PathVariable Long lootId) {
        String content = orderService.purchaseLoot(lootId);
        return ResponseEntity.ok(content);
    }
}
