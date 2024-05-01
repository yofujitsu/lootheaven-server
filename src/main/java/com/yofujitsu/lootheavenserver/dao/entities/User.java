package com.yofujitsu.lootheavenserver.dao.entities;


import com.yofujitsu.lootheavenserver.dao.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Loot> userLoots;

    @OneToMany(mappedBy = "buyerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> purchases;

    // Orders where the user is the seller
    @OneToMany(mappedBy = "sellerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> sales;

    @Column(name = "regDate")
    private LocalDate regDate;

    @Column(name = "dealsCount")
    private Long dealsCount;

    @Column(name = "ordersCount")
    private Long ordersCount;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "isBanned")
    private boolean active;
}
