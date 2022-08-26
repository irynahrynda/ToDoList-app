package com.example.todolistapp.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @Size(max = 30)
    private String name;
    @Email(message = "Email is not valid", regexp = "^(.+)@(.+)$")
    private String email;
    @NotEmpty(message = "The password couldn't be empty")
    @Size(min = 8, message = "Password must be at least 8 symbols long")
    private String password;
}
