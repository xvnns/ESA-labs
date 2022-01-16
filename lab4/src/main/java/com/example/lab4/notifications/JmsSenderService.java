package com.example.lab4.notifications;

import com.example.lab4.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JmsSenderService {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsSenderService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendOrderUpdate(Order order, EventType eventType){
        Email email = new Email();
        Set<LineItem> items = order.getLineItems();
        email.setReceiver(order.getCustomerEmail());
        email.setSubject("Order [" + eventType.name() + ']');
        String bodyPattern = "Заказ изменен.\n" +
                "Тип изменения: %s\n\n" +
                "Продукты: %s";
        String body = String.format(bodyPattern, eventType.name(), items.toString());
        email.setBody(body);
        jmsTemplate.convertAndSend("mailbox", email);
    }

    public <T> void sendEvent(Class<T> entityClass, T entity, EventType eventType){
        Event event = new Event();
        event.setEventType(eventType);
        event.setEntity(entity.toString());
        event.setEntityClass(entityClass.getSimpleName());
        jmsTemplate.convertAndSend("eventbox", event);
    }
}
