package com.demo.command;

import com.demo.command.service.CommandDispatcher;
import com.demo.command.service.UserCommandHandler;
import com.demo.domain.command.user.CreateUserCommand;
import com.demo.domain.command.user.DeleteUserCommand;
import com.demo.domain.command.user.UpdateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@ComponentScan(basePackages = {"com.demo.command", "com.demo.domain"})
@SpringBootApplication
public class CommandApplication {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @Autowired
    private UserCommandHandler userCommandHandler;

    public static void main(String[] args) {
        SpringApplication.run(CommandApplication.class, args);
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(CreateUserCommand.class, userCommandHandler::handle);
        commandDispatcher.registerHandler(UpdateUserCommand.class, userCommandHandler::handle);
        commandDispatcher.registerHandler(DeleteUserCommand.class, userCommandHandler::handle);
    }

}
