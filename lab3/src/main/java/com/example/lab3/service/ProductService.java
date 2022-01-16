package com.example.lab3.service;

import com.example.lab3.entity.Product;
import com.example.lab3.repository.ProductRepository;
import com.example.lab3.response.BadResponse;
import com.example.lab3.response.GoodResponse;
import com.example.lab3.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public ServerResponse add(Long serialNumber) {
        Product product = new Product();
        product.setSerialNumber(serialNumber);
        Product newProduct = productRepository.save(product);
        return new GoodResponse(newProduct);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ServerResponse delete(Long id) {
        Product product = findById(id);
        if (product == null) {
            return new BadResponse("Product not found");
        }
        productRepository.delete(product);
        return new GoodResponse();
    }
}
