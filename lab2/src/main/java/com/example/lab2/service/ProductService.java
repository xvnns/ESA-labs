package com.example.lab2.service;

import com.example.lab2.entity.Product;
import com.example.lab2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public Product add(Long serialNumber) {
        Product product = new Product();
        product.setSerialNumber(serialNumber);
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        Product product = findById(id);
        if (product != null) {
            productRepository.delete(product);
        }
    }
}
