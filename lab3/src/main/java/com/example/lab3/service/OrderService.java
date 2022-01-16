package com.example.lab3.service;

import com.example.lab3.entity.Order;
import com.example.lab3.repository.OrderRepository;
import com.example.lab3.response.BadResponse;
import com.example.lab3.response.GoodResponse;
import com.example.lab3.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public boolean findByCustomerEmail(String email) {
        return orderRepository.existsByCustomerEmail(email);
    }

    public ServerResponse add(String customerEmail) {
        if (findByCustomerEmail(customerEmail)) {
            return new BadResponse("Duplicate email");
        }
        Order order = new Order();
        order.setCustomerEmail(customerEmail);
        order.setDate(new Date());
        Order newOrder = orderRepository.save(order);
        return new GoodResponse(newOrder);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public ServerResponse delete(Long id) {
        Order order = findById(id);
        if (order == null) {
            return new BadResponse("Order not found");
        }
        if (!order.getLineItems().isEmpty())
        {
            return new BadResponse("Order has products");
        }
        orderRepository.delete(order);
        return new GoodResponse();
    }
}
