package com.example.lab4.service;

import com.example.lab4.entity.EventType;
import com.example.lab4.entity.Order;
import com.example.lab4.jms.JmsSenderService;
import com.example.lab4.repository.OrderRepository;
import com.example.lab4.response.BadResponse;
import com.example.lab4.response.GoodResponse;
import com.example.lab4.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    private final JmsSenderService jmsSenderService;

    @Autowired
    public OrderService(OrderRepository orderRepository, JmsSenderService jmsSenderService) {
        this.orderRepository = orderRepository;
        this.jmsSenderService = jmsSenderService;
    }

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
        jmsSenderService.sendEvent(Order.class, newOrder, EventType.CREATE);
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
        jmsSenderService.sendEvent(Order.class, order, EventType.DELETE);
        return new GoodResponse();
    }
}
