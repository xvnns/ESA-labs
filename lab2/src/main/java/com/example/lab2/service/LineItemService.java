package com.example.lab2.service;

import com.example.lab2.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineItemService {

    @Autowired
    public LineItemRepository lineItemRepository;
}
