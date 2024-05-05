package com.yofujitsu.lootheavenserver.controllers;

import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.entities.dto.LootDTO;
import com.yofujitsu.lootheavenserver.services.LootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loots")
public class LootController {

    @Autowired
    private LootService lootService;

    @PostMapping("/add")
    public ResponseEntity<LootDTO> createLoot(@RequestBody LootDTO lootDTO) {
        try {
            LootDTO createdLoot = lootService.createLoot(lootDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLoot);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<LootDTO>> getAllLoots() {
        List<LootDTO> loots = lootService.findAllLoots();
        return loots.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loots);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<LootDTO>> getLootsByUserId(@PathVariable String userId) {
        List<LootDTO> loots = lootService.findLootsByUserId(Long.parseLong(userId));
        return loots.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loots);
    }

    @GetMapping("/my")
    public ResponseEntity<List<LootDTO>> getLootsForCurrentUser() {
        List<LootDTO> loots = lootService.findLootsForCurrentUser();
        return loots.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loots);
    }

    @DeleteMapping("/del/{lootId}")
    public ResponseEntity<Void> deleteLoot(@PathVariable String lootId) {
        try {
            lootService.deleteLoot(Long.parseLong(lootId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/update/{lootId}")
    public ResponseEntity<Loot> updateLoot(@PathVariable String lootId, @RequestBody LootDTO lootDTO) {
        try {
            Loot updatedLoot = lootService.updateLoot(Long.parseLong(lootId), lootDTO);
            return ResponseEntity.ok(updatedLoot);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAllLoots() {
        try {
            lootService.deleteAllLoots();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

