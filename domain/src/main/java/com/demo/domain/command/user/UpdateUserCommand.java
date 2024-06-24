package com.demo.domain.command.user;
import com.demo.shared.base.BaseCommand;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUserCommand extends BaseCommand {

    private String id;
    private String userCode;
    @Size(min = 6, max = 15, message = "Username phải có từ 6 đến 15 ký tự")
    @NotBlank(message = "Không được để trống username")
    @Pattern(regexp = "\\S+", message = "Username không được chứa khoảng trắng")
    private String username;
    @Size(min = 6, max = 15, message = "Username phải có từ 6 đến 15 ký tự")
    @NotBlank(message = "Không được để trống username")
    @Pattern(regexp = "\\S+", message = "Username không được chứa khoảng trắng")
    private String password;
    private Integer role;
    private Boolean isDeleted;

}
