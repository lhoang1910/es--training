package com.demo.command.service.impl;

import com.demo.command.service.EventSourcingHandler;
import com.demo.domain.aggregate.UserAggregate;
import com.demo.domain.command.user.CreateUserCommand;
import com.demo.domain.command.user.DeleteUserCommand;
import com.demo.domain.command.user.UpdateUserCommand;
import com.demo.command.service.UserCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandlerImpl implements UserCommandHandler {

    @Autowired
    private EventSourcingHandler<UserAggregate> eventSourcingHandler;

    @Override
    public void handle(CreateUserCommand command) {
        UserAggregate aggregate = new UserAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        UserAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deleteUser();
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateUserCommand command) {
        UserAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.updateUser(command);
        eventSourcingHandler.save(aggregate);
    }
}
