package com.example.lab4.jms;

import com.example.lab4.entity.Event;

public interface Receiver {
    void receive(Event event);
}
