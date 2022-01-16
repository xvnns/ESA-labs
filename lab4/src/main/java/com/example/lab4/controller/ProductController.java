package com.example.lab4.controller;

import com.example.lab4.entity.Product;
import com.example.lab4.response.ServerResponse;
import com.example.lab4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/add")
    public ServerResponse add(Long serialNumber) {
        return productService.add(serialNumber);
    }

    @GetMapping("/delete")
    public ServerResponse delete(Long id) {
        return productService.delete(id);
    }
}
