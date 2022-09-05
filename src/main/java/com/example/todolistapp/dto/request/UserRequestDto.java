package com.example.todolistapp.dto.request;

import com.example.todolistapp.lib.FieldsValueMatch;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Data
public class UserRequestDto {
    @Size(max = 20)
    private String name;
    @NotNull
    @Email(message = "Email is not valid", regexp = "^(.+)@(.+)$")
    private String email;
    @NotNull
    @NotEmpty(message = "The password couldn't be empty")
    @Size(min = 8, message = "Password must be at least 8 symbols long")
    private String password;
    @NotNull
    private String repeatPassword;
}
