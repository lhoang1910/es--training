package com.demo.domain.command.user;

import com.demo.shared.base.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCommand extends BaseCommand {

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

}
