package com.example.lab2.controller;

import com.example.lab2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String findAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/add/{serial-number}")
    public String add(@PathVariable("serial-number") Long serialNumber) {
        productService.add(serialNumber);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
}
