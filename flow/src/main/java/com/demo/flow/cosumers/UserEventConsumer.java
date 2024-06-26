package com.demo.flow.cosumers;

import com.demo.domain.event.user.UserCreatedEvent;
import com.demo.domain.event.user.UserDeletedEvent;
import com.demo.domain.event.user.UserUpdatedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface UserEventConsumer {
    void consume(@Payload UserCreatedEvent event);
    void consume(@Payload UserUpdatedEvent event);
    void consume(@Payload UserDeletedEvent event);
}
