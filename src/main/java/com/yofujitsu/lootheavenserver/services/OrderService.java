package com.yofujitsu.lootheavenserver.services;

import com.yofujitsu.lootheavenserver.Mappers.LootMapper;
import com.yofujitsu.lootheavenserver.Mappers.OrderMapper;
import com.yofujitsu.lootheavenserver.dao.entities.Loot;
import com.yofujitsu.lootheavenserver.dao.entities.Order;
import com.yofujitsu.lootheavenserver.dao.entities.User;
import com.yofujitsu.lootheavenserver.dao.entities.dto.LootDTO;
import com.yofujitsu.lootheavenserver.dao.entities.dto.OrderDTO;
import com.yofujitsu.lootheavenserver.dao.entities.dto.UserDTO;
import com.yofujitsu.lootheavenserver.dao.repositories.LootRepository;
import com.yofujitsu.lootheavenserver.dao.repositories.OrderRepository;
import com.yofujitsu.lootheavenserver.dao.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    private OrderMapper orderMapper;

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
                order.setStatus("completed");
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

    public OrderDTO getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.map(orderMapper::orderToOrderDTO).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
    }

    public List<OrderDTO> getMyOrders() {
        User currUser = userService.getCurrentUser();
        Long userId = currUser.getId();
        List<Order> sellerOrders = orderRepository.findBySellerId(userId);
        List<Order> buyerOrders = orderRepository.findByBuyerId(userId);

        return Stream.concat(sellerOrders.stream(), buyerOrders.stream())
                .distinct() // Удаление дубликатов, если они есть
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

}
