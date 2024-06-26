package com.demo.domain.aggregate;

import com.demo.domain.command.user.CreateUserCommand;
import com.demo.domain.command.user.UpdateUserCommand;
import com.demo.domain.event.user.UserCreatedEvent;
import com.demo.domain.event.user.UserDeletedEvent;
import com.demo.domain.event.user.UserUpdatedEvent;
import com.demo.shared.base.BaseAggregate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserAggregate extends BaseAggregate {

    private String userCode;
    private String username;
    private String password;
    private Integer role;
    private Boolean isDeleted;
    private Date createdDate;

    public UserAggregate(CreateUserCommand command){
        raiseEvent(UserCreatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .userCode(command.getUserCode())
                .username(command.getUsername())
                .password(command.getPassword())
                .role(command.getRole())
                .isDeleted(command.getIsDeleted())
                .createdDate(new Date())
                .build());
    }

    public void apply(UserCreatedEvent event){
        this.id = event.getId();
        this.username = event.getUsername();
        this.password = event.getPassword();
        this.userCode = event.getUserCode();
        this.role = event.getRole();
        this.isDeleted = event.getIsDeleted();

    }

    public void deleteUser(){

        if (this.isDeleted){
            throw new IllegalStateException("The user has been previously deleted");
        }

        raiseEvent(UserDeletedEvent.builder()
                .id(this.id)
                .isDeleted(true)
                .deletedDate(new Date())
                .build());
    }

    public void apply(UserDeletedEvent event) {
        this.id = event.getId();
        this.isDeleted = true;
    }

    public void updateUser(UpdateUserCommand command){

        if (!this.id.equals(command.getId())) {
            throw new IllegalArgumentException("ID is not valid");
        }

        raiseEvent(UserUpdatedEvent.builder()
                .id(command.getId())
                .userCode(command.getUserCode())
                .username(command.getUsername())
                .password(command.getPassword())
                .role(command.getRole())
                .isDeleted(command.getIsDeleted())
                .updatedDate(new Date())
                .build());
    }

    public void apply(UserUpdatedEvent event) {
        this.id = event.getId();
        this.username = event.getUsername();
        this.password = event.getPassword();
        this.userCode = event.getUserCode();
        this.role = event.getRole();
        this.isDeleted = event.getIsDeleted();
        this.createdDate = event.getUpdatedDate();
    }

}
