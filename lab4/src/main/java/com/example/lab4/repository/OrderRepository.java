package com.example.lab4.repository;

import com.example.lab4.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public boolean existsByCustomerEmail(String email);
}
