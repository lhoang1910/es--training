package com.demo.command.service;

import com.demo.domain.command.user.CreateUserCommand;
import com.demo.domain.command.user.DeleteUserCommand;
import com.demo.domain.command.user.UpdateUserCommand;

public interface UserCommandHandler {
    void handle(CreateUserCommand command);
    void handle(DeleteUserCommand command);
    void handle(UpdateUserCommand command);
}
