package com.yofujitsu.lootheavenserver.controllers;


import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.repositories.LootRepository;
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
    @Autowired
    private LootRepository lootRepository;

    @PostMapping("/add")
    public ResponseEntity<Loot> createLoot(@RequestBody Loot loot) {
        try {
            Loot savedLoot = lootService.createLoot(loot);
            return new ResponseEntity<>(savedLoot, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Loot>> getAllLoots() {
        List<Loot> loots = lootService.findAllLoots();
        return loots.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loots);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Loot>> getLootsByUserId(@PathVariable Long userId) {
        List<Loot> loots = lootService.findLootsByUserId(userId);
        return loots.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loots);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Loot>> getLootsForCurrentUser() {
        List<Loot> loots = lootService.findLootsForCurrentUser();
        return loots.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loots);
    }

    @DeleteMapping("/del/{lootId}")
    public ResponseEntity<Void> deleteLoot(@PathVariable Long lootId) {
        boolean isDeleted = lootService.deleteLootIfOwned(lootId);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
