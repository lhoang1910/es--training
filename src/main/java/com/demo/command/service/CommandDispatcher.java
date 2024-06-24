package com.demo.command.service;

import com.demo.domain.command.CommandHandlerMethod;
import com.demo.shared.base.BaseCommand;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
