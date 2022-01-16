package com.example.lab1.bean;

import com.example.lab1.dao.OrderDAO;
import com.example.lab1.entity.Order;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class MainBean {

    private final OrderDAO orderDAO = new OrderDAO();

    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }
}
