package com.yofujitsu.lootheavenserver.dao.entities;

import com.yofujitsu.lootheavenserver.dao.entities.enums.LootType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "loots")
public class Loot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lootName")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private LootType type;

    @Column(name = "desc")
    private String desc;

    @Column(name = "price")
    private Long price;

    @Column(name = "status")
    private String status;

    @Column(name = "productName")
    private String productName; //game or service (spotify etc..)

    @Column(name = "userId")
    private Long userId;

    @Column(name = "published")
    private LocalDateTime published;

}
