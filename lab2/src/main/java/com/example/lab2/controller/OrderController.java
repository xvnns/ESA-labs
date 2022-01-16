package com.example.lab2.controller;

import com.example.lab2.entity.Order;
import com.example.lab2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public String getOrderList(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order";
    }

    @GetMapping("/add")
    public String addOrder() {
        orderService.add();
        return "redirect:/order/list";
    }

    @GetMapping("/delete/{id}")
    public String getOrderList(@PathVariable("id") Long id) {
        orderService.delete(id);
        return "redirect:/order/list";
    }
}
