package com.example.lab3.controller;

import com.example.lab3.response.ServerResponse;
import com.example.lab3.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class LineItemController {

    @Autowired
    private LineItemService lineItemService;

    @GetMapping("/add")
    public ServerResponse add(Long orderId, Long productId, Long quantity) {
        return lineItemService.add(orderId, productId, quantity);
    }

    @GetMapping("/delete")
    public ServerResponse delete(Long id) {
        return lineItemService.delete(id);
    }
}
