package com.demo.command.service;

import com.demo.domain.command.user.CreateUserCommand;
import com.demo.domain.command.user.DeleteUserCommand;
import com.demo.domain.command.user.UpdateUserCommand;
import com.demo.shared.response.WrapResponse;

public interface UserService {

    WrapResponse<?> createUser(CreateUserCommand command);
    WrapResponse<?> deleteUser(String id, DeleteUserCommand command);
    WrapResponse<?> updateUser(String id, UpdateUserCommand command);

}
