package com.yofujitsu.lootheavenserver.dao.repositories;

import com.yofujitsu.lootheavenserver.dao.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findBySellerId(Long sellerId);
    List<Order> findByBuyerId(Long buyerId);
}
