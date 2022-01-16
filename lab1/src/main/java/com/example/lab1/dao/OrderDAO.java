package com.example.lab1.dao;

import com.example.lab1.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class OrderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public OrderDAO() {
        this.entityManager = EntityManagerCreator.createManager();
    }

    public Order getById(Long id) {
        return (Order) entityManager.createQuery("select order from Order order where order.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    public List<Order> getAll() {
        return (List<Order>) entityManager.createQuery("select order from Order order").getResultList();
    }

    public Order save(Order order) {
        entityManager.getTransaction().begin();
        Order savedOrder = entityManager.merge(order);
        entityManager.getTransaction().commit();
        return savedOrder;
    }

    public void update(Order order){
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(getById(id));
        entityManager.getTransaction().commit();
    }
}
