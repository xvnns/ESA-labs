package com.example.lab4.jms;

import com.example.lab4.entity.Email;
import com.example.lab4.entity.Event;
import com.example.lab4.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailReceiver implements Receiver {

    private final EmailService emailService;

    @Autowired
    public EmailReceiver(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = "event")
    public void receive(Event event) {
        String body = "Данные изменены.\nТип изменения: %s";
        String message = String.format(body, event.getEventType().name());
        Email email = new Email(message, "xxx@xxx.ru");
        emailService.save(email);
    }
}
