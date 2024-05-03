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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lootName")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private LootType type;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "status")
    private String status;

    @Column(name = "productName")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "creatorId")
    private User creator;

    @Column(name = "published")
    private LocalDateTime published;

    @Column(name = "contentUrl", nullable = true)
    private String contentUrl;

}
