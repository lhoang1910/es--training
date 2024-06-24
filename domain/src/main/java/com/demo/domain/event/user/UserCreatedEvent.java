package com.demo.domain.event.user;

import com.demo.shared.base.BaseEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
public class UserCreatedEvent extends BaseEvent {

    private String id;
    private String userCode;

    @Size(min = 6, max = 15, message = "Username phải có từ 6 đến 15 ký tự")
    @NotBlank(message = "Không được để trống username")
    @Pattern(regexp = "\\S+", message = "Username không được chứa khoảng trắng")
    private String username;

    @Size(min = 8, max = 15, message = "Password phải có từ 8 đến 15 ký tự")
    @NotBlank(message = "Không được để trống password")
    @Pattern(regexp = "\\S+", message = "Password không được chứa khoảng trắng")
    private String password;

    private Integer role;
    private Boolean isDeleted;
    private Date createdDate;
}
