package com.yofujitsu.lootheavenserver.dao.repositories;

import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LootRepository extends JpaRepository<Loot, Long> {
}
