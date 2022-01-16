package com.example.lab4.controller;

import com.example.lab4.entity.Order;
import com.example.lab4.response.ServerResponse;
import com.example.lab4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public Iterable<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/add")
    public ServerResponse add(String customerEmail) {
        return orderService.add(customerEmail);
    }

    @GetMapping("/delete")
    public ServerResponse delete(Long id) {
        return orderService.delete(id);
    }
}
