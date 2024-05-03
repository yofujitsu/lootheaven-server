package com.yofujitsu.lootheavenserver.services;

import com.yofujitsu.lootheavenserver.Mappers.LootMapper;
import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.entities.dto.LootDTO;
import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;
import com.yofujitsu.lootheavenserver.dao.repositories.LootRepository;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Map;
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
        Loot loot = LootMapper.INSTANCE.lootDTOToLoot(lootDTO);
        loot.setCreator(userRepository.findById(lootDTO.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        loot = lootRepository.save(loot);
        return LootMapper.INSTANCE.lootToLootDTO(loot);
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

    public Loot updateLoot(Long lootId, Loot updateData) {
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
                .map(LootMapper.INSTANCE::lootToLootDTO)
                .collect(Collectors.toList());
    }

    public List<LootDTO> findLootsByUserId(Long discordId) {
        return lootRepository.findByCreatorId(discordId).stream()
                .map(LootMapper.INSTANCE::lootToLootDTO)
                .collect(Collectors.toList());
    }

    public List<LootDTO> findLootsForCurrentUser() {
        User currUser = userService.getCurrentUser();
        Long discordId = parseLong(currUser.getDiscordId());
        return lootRepository.findByCreatorId(discordId).stream()
                .map(LootMapper.INSTANCE::lootToLootDTO)
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
