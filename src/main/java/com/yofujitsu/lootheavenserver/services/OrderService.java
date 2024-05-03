package com.yofujitsu.lootheavenserver.services;

import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.entities.Order;
import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.repositories.LootRepository;
import com.yofujitsu.lootheavenserver.dao.repositories.OrderRepository;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LootRepository lootRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public String purchaseLoot(Long lootId) {
        User buyer = userService.getCurrentUser();
        Loot loot = lootRepository.findById(lootId).orElseThrow(RuntimeException::new);
        User seller = userRepository.findByDiscordId(loot.getCreator().getDiscordId());
        if(!Objects.equals(buyer.getId(), seller.getId())) {
            if (buyer.getBalance() >= loot.getPrice()) {
                buyer.setBalance(buyer.getBalance() - loot.getPrice());
                seller.setBalance(seller.getBalance() + loot.getPrice());
                loot.setStatus("sold");

                Order order = new Order();
                order.setBuyer(buyer);
                order.setSeller(seller);
                order.setLoot(loot);
                order.setOrderDate(LocalDateTime.now());
                order.setCost(loot.getPrice());
                orderRepository.save(order);

                buyer.setOrdersCount(buyer.getOrdersCount() + 1);
                seller.setDealsCount(seller.getDealsCount() + 1);
                userRepository.save(buyer);
                userRepository.save(seller);
                lootRepository.save(loot);
            } else {
                throw new RuntimeException("Insufficient funds");
            }
        }
        return loot.getContentUrl();
    }
}
