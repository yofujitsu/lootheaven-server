package com.yofujitsu.lootheavenserver.controllers;


import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.entities.dto.LootDTO;
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
    public ResponseEntity<LootDTO> createLoot(@RequestBody LootDTO lootDTO) {
        try {
            LootDTO createdLoot = lootService.createLoot(lootDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<LootDTO>> getAllLoots() {
        List<LootDTO> loots = lootService.findAllLoots();
        return loots.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loots);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<LootDTO>> getLootsByUserId(@PathVariable Long userId) {
        List<LootDTO> loots = lootService.findLootsByUserId(userId);
        return loots.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loots);
    }

    @GetMapping("/my")
    public ResponseEntity<List<LootDTO>> getLootsForCurrentUser() {
        List<LootDTO> loots = lootService.findLootsForCurrentUser();
        return loots.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loots);
    }

    @DeleteMapping("/del/{lootId}")
    public ResponseEntity<Void> deleteLoot(@PathVariable Long lootId) {
        lootService.deleteLoot(lootId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{lootId}")
    public ResponseEntity<Loot> updateLoot(@PathVariable Long lootId, @RequestBody Loot loot) {
        Loot updatedLoot = lootService.updateLoot(lootId, loot);
        return ResponseEntity.ok(updatedLoot);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAllLoots() throws Exception {
        lootService.deleteAllLoots();
        return ResponseEntity.ok().build();
    }

}
