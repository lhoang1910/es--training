package com.demo.flow.cosumers.impl;

import com.demo.domain.event.user.UserCreatedEvent;
import com.demo.domain.event.user.UserDeletedEvent;
import com.demo.domain.event.user.UserUpdatedEvent;
import com.demo.flow.cosumers.UserEventConsumer;
import com.demo.flow.event.handler.UserEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//@EnableKafka
@Component
public class UserEventConsumerImpl implements UserEventConsumer {

    @Autowired
    UserEventHandler userEventHandler;

    @KafkaListener(topics = {"UserCreatedEvent"}, groupId = "insuranceConsumer")
    @Override
    public void consume(@Payload UserCreatedEvent event) {
        userEventHandler.on(event);
    }

    @KafkaListener(topics = "UserUpdatedEvent", groupId = "insuranceConsumer")
    @Override
    public void consume(@Payload UserUpdatedEvent event) {
        userEventHandler.on(event);
    }

    @KafkaListener(topics = "UserDeletedEvent")
    @Override
    public void consume(@Payload UserDeletedEvent event) {
        userEventHandler.on(event);
    }
}
