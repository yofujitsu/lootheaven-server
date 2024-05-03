package com.yofujitsu.lootheavenserver.dao.repositories;

import com.yofujitsu.lootheavenserver.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
   User findByDiscordId(String discordId);
}
