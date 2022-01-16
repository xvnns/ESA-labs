package com.example.lab1.dao;

import com.example.lab1.entity.LineItem;
import com.example.lab1.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public ProductDAO() {
        this.entityManager = EntityManagerCreator.createManager();
    }

    public Product getById(Long id) {
        return (Product) entityManager.createQuery("select product from Product product where product.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    public List<Product> getAll() {
        return (List<Product>) entityManager.createQuery("select product from Product product").getResultList();
    }

    public Product save(Product product) {
        entityManager.getTransaction().begin();
        Product savedProduct = entityManager.merge(product);
        entityManager.getTransaction().commit();
        return savedProduct;
    }

    public void update(Product product){
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(getById(id));
        entityManager.getTransaction().commit();
    }
}
