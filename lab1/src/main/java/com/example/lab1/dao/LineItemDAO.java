package com.example.lab1.dao;

import com.example.lab1.entity.LineItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class LineItemDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public LineItemDAO() {
        this.entityManager = EntityManagerCreator.createManager();
    }

    public LineItem getById(Long id) {
        return (LineItem) entityManager.createQuery("select lineItem from LineItem lineItem where lineItem.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    public List<LineItem> getAll() {
        return (List<LineItem>) entityManager.createQuery("select lineItem from LineItem lineItem").getResultList();
    }

    public LineItem save(LineItem lineItem) {
        entityManager.getTransaction().begin();
        LineItem savedLineItem = entityManager.merge(lineItem);
        entityManager.getTransaction().commit();
        return savedLineItem;
    }

    public void update(LineItem lineItem){
        entityManager.getTransaction().begin();
        entityManager.merge(lineItem);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(getById(id));
        entityManager.getTransaction().commit();
    }
}
