package com.yofujitsu.lootheavenserver.dao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "buyerId")
    private Long buyerId;

    @Column(name = "sellerId")
    private Long sellerId;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "status")
    private String status;

    @Column(name = "lootId")
    private Long lootId;

    @Column(name = "transactionCost")
    private Long cost;
}
