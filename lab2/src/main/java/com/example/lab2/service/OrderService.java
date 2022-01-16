package com.example.lab2.service;

import com.example.lab2.entity.Order;
import com.example.lab2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order add() {
        Order order = new Order();
        order.setDate(new Date());
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        Order order = findById(id);
        if (order != null) {
            orderRepository.delete(order);
        }
    }
}
