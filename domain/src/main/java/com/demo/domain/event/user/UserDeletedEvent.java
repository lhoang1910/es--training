package com.demo.domain.event.user;

import com.demo.shared.base.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDeletedEvent extends BaseEvent {
    private String id;
    private Boolean isDeleted;
    private Date deletedDate;
}
