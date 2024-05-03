package com.yofujitsu.lootheavenserver.dao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyerId")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    private User seller;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "lootId", referencedColumnName = "id")
    private Loot loot;

    @Column(name = "transactionCost")
    private Long cost;
}
