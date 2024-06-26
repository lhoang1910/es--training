package com.demo.flow.event.handler;

import com.demo.domain.event.user.UserCreatedEvent;
import com.demo.domain.event.user.UserDeletedEvent;
import com.demo.domain.event.user.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserCreatedEvent event);
    void on(UserUpdatedEvent event);
    void on(UserDeletedEvent event);
}
