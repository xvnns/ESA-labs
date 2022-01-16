package com.example.lab1.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerCreator {
    public static EntityManager createManager() {
        return Persistence.createEntityManagerFactory("esa-lab").createEntityManager();
    }
}
