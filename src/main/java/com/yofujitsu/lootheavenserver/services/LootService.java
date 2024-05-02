package com.yofujitsu.lootheavenserver.services;

import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.repositories.LootRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LootService {

    @Autowired
    private LootRepository lootRepository;

    @Transactional
    public Loot createLoot(Loot loot) {
        return lootRepository.save(loot);
    }

    public void deleteLoot(Long id) {
        lootRepository.deleteById(id);
    }

    public boolean deleteLootIfOwned(Long lootId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User oidcUser = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oidcUser.getAttributes();
        Long id = Long.parseLong((String) attributes.get("id"));
        Optional<Loot> loot = lootRepository.findById(lootId);

        if (loot.isPresent() && loot.get().getUserId().equals(id)) {
            deleteLoot(lootId);
            return true;
        }
        return false;
    }

    public List<Loot> findAllLoots() {
        return lootRepository.findAll();
    }

    public List<Loot> findLootsByUserId(Long discordId) {
        return lootRepository.findByUserId(discordId);
    }

    public List<Loot> findLootsForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User oidcUser = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oidcUser.getAttributes();
        Long id = Long.parseLong((String) attributes.get("id"));
        return lootRepository.findByUserId(id);
    }

}
