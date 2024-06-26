package com.demo.command.service.impl;

import com.demo.command.service.CommandDispatcher;
import com.demo.command.service.UserService;
import com.demo.domain.command.user.CreateUserCommand;
import com.demo.domain.command.user.DeleteUserCommand;
import com.demo.domain.command.user.UpdateUserCommand;
import com.demo.shared.response.WrapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    public WrapResponse<?> createUser(CreateUserCommand command) {
        command.setId(String.valueOf(UUID.randomUUID()));
        try {
            commandDispatcher.send(command);
            return new WrapResponse<>(true, HttpStatus.OK.value(), "Create new user successful");
        } catch (Exception e) {
            String eMessage = "Error while processing request to create a new user. " + e;
            logger.log(Level.SEVERE, eMessage, e);
            return new WrapResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),  eMessage);
        }
    }

    public WrapResponse<?> deleteUser(String id) {
        try {
            commandDispatcher.send(new DeleteUserCommand(id));
            return new WrapResponse<>(true, HttpStatus.OK.value(), "Delete user successful");
        } catch (Exception e) {
            String eMessage = "Error while processing request to delete user. " + e;
            logger.log(Level.SEVERE, eMessage, e);
            return new WrapResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),  eMessage);
        }
    }

    public WrapResponse<?> updateUser(String id, UpdateUserCommand command) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new WrapResponse<>(true, HttpStatus.OK.value(), "Update user successful");
        } catch (Exception e) {
            String eMessage = "Error while processing request to update user. " + e;
            logger.log(Level.SEVERE, eMessage, e);
            return new WrapResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),  eMessage);
        }
    }
}
