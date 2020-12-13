package com.example.SpringSecurityHW8.services;

import com.example.SpringSecurityHW8.entities.Order;
import com.example.SpringSecurityHW8.entities.User;
import com.example.SpringSecurityHW8.repositories.OrderRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;
    private UserService userService;

    public OrderService(OrderRepository orderRepository, CartService cartService, UserService userService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAll(Specification<Order> spec) {
        return orderRepository.findAll(spec);
    }

    @Transactional
    public Order createOrder() {
        Order order = new Order();
        order.setCode(UUID.randomUUID().toString().substring(0,4));
        User user = userService.getCurrentUser();
        System.out.println("createOrder user = " + user);
        order.setUser(user);
        order.setTotalPrice(cartService.getTotalPrice());
        order.setOrderEntries(cartService.getOrderEntries());
        cartService.getOrderEntries().stream().forEach(orderEntry -> {
            orderEntry.setOrder(order);
        });
        orderRepository.save(order);
        cartService.clearCart();
        return order;
    }

    public void remove(Long id) {
        orderRepository.deleteById(id);
    }
}
