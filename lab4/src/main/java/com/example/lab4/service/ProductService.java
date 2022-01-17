package com.example.lab4.service;

import com.example.lab4.entity.EventType;
import com.example.lab4.entity.Product;
import com.example.lab4.jms.JmsSenderService;
import com.example.lab4.repository.ProductRepository;
import com.example.lab4.response.BadResponse;
import com.example.lab4.response.GoodResponse;
import com.example.lab4.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    public final ProductRepository productRepository;

    private final JmsSenderService jmsSenderService;

    @Autowired
    public ProductService(ProductRepository productRepository, JmsSenderService jmsSenderService) {
        this.productRepository = productRepository;
        this.jmsSenderService = jmsSenderService;
    }

    public ServerResponse add(Long serialNumber) {
        Product product = new Product();
        product.setSerialNumber(serialNumber);
        Product newProduct = productRepository.save(product);
        jmsSenderService.sendEvent(Product.class, newProduct, EventType.CREATE);
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
        jmsSenderService.sendEvent(Product.class, product, EventType.DELETE);
        return new GoodResponse();
    }
}
