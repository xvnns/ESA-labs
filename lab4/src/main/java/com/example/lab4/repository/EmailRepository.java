package com.example.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lab4.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Long>  {
}
