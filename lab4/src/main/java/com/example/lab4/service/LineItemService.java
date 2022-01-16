package com.example.lab4.service;

import com.example.lab4.entity.EventType;
import com.example.lab4.entity.LineItem;
import com.example.lab4.entity.Order;
import com.example.lab4.entity.Product;
import com.example.lab4.notifications.JmsSenderService;
import com.example.lab4.repository.LineItemRepository;
import com.example.lab4.response.BadResponse;
import com.example.lab4.response.GoodResponse;
import com.example.lab4.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineItemService {

    @Autowired
    private LineItemRepository lineItemRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private JmsSenderService jmsSenderService;

    public ServerResponse add(Long orderId, Long productId, Long quantity) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return new BadResponse("Order not found");
        }
        Product product = productService.findById(productId);
        if (product == null) {
            return new BadResponse("Product not found");
        }
        LineItem item = new LineItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);
        jmsSenderService.sendEvent(LineItem.class, item, EventType.CREATE);
        return new GoodResponse(item);
    }

    public LineItem findById(Long id) {
        return lineItemRepository.findById(id).orElse(null);
    }

    public ServerResponse delete(Long id) {
        LineItem item = findById(id);
        if (item == null) {
            return new BadResponse("Item not found");
        }
        lineItemRepository.delete(item);
        jmsSenderService.sendEvent(LineItem.class, item, EventType.DELETE);
        return new GoodResponse();
    }
}
