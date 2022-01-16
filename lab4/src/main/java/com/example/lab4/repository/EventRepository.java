package com.example.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lab4.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
