package com.demo.domain.command.user;

import com.demo.shared.base.BaseCommand;
import lombok.Data;

@Data
public class DeleteUserCommand  extends BaseCommand {
    public DeleteUserCommand(String id) {
        super(id);
    }
}
