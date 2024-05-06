package com.yofujitsu.lootheavenserver.services;

import com.yofujitsu.lootheavenserver.Mappers.LootMapper;
import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.entities.dto.LootDTO;
import com.yofujitsu.lootheavenserver.dao.entities.dto.UserDTO;
import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;
import com.yofujitsu.lootheavenserver.dao.repositories.LootRepository;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

@Service
public class LootService {

    @Autowired
    private LootRepository lootRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LootMapper lootMapper;

    @Transactional
    public LootDTO createLoot(LootDTO lootDTO) {
        Loot loot = LootMapper.lootDTOToLoot(lootDTO);
        loot.setCreator(userRepository.findById(lootDTO.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        loot = lootRepository.save(loot);
        return lootMapper.lootToLootDTO(loot);
    }


    public void deleteLoot(Long id) {
        if (LootIfOwned(id)) {
            lootRepository.deleteById(id);
        }
    }

    public boolean LootIfOwned(Long lootId) {
        User owner = userService.getCurrentUser();
        Optional<Loot> loot = lootRepository.findById(lootId);
        return loot.isPresent() && loot.get().getCreator().getDiscordId().equals(owner.getDiscordId());
    }

    public Loot updateLoot(Long lootId, LootDTO updateData) {
        Loot loot = lootRepository.findById(lootId)
                .orElseThrow(() -> new NotFoundException("Loot not found with id: " + lootId));
        if (LootIfOwned(lootId)) {
            loot.setName(updateData.getName() != null ? updateData.getName() : loot.getName());
            loot.setContentUrl(updateData.getContentUrl() != null ? updateData.getContentUrl() : loot.getContentUrl());
            loot.setStatus(updateData.getStatus() != null ? updateData.getStatus() : loot.getStatus());
            loot.setPrice(updateData.getPrice() != null ? updateData.getPrice() : loot.getPrice());
        }
        return lootRepository.save(loot);
    }

    public List<LootDTO> findAllLoots() {
        return lootRepository.findAll().stream()
                .map(lootMapper::lootToLootDTO)
                .collect(Collectors.toList());
    }

    public LootDTO findLootById(Long lootId) {
        Optional<Loot> loot = lootRepository.findById(lootId);
        return loot.map(lootMapper::lootToLootDTO).orElseThrow(() -> new EntityNotFoundException("Loot not found with id: " + lootId));
    }

    public List<LootDTO> findLootsByUserId(Long discordId) {
        return lootRepository.findByCreatorId(discordId).stream()
                .map(lootMapper::lootToLootDTO)
                .collect(Collectors.toList());
    }

    public List<LootDTO> findLootsForCurrentUser() {
        User currUser = userService.getCurrentUser();
        Long userId = currUser.getId();
        return lootRepository.findByCreatorId(userId).stream()
                .map(lootMapper::lootToLootDTO)
                .collect(Collectors.toList());
    }

    public void deleteAllLoots() throws Exception {
        User currUser = userService.getCurrentUser();
        if (currUser.getRole().equals(UserRole.ADMIN)) {
            lootRepository.deleteAll();
        }
        else {
            throw new Exception();
        }
    }

}
