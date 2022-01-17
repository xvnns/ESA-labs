package com.example.lab4.jms;

import com.example.lab4.entity.Event;
import com.example.lab4.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EventReceiver implements Receiver {
    private final EventService eventService;

    @Autowired
    public EventReceiver(EventService eventService) {
        this.eventService = eventService;
    }

    @JmsListener(destination = "event")
    public void receive(Event event) {
        eventService.save(event);
    }
}
