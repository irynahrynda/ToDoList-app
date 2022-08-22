package com.example.todolistapp.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 30)
    private String name;
    @Email(message = "Email is not valid", regexp = "^(.+)@(.+)$")
    private String email;
    @Size(min = 8, max = 40)
    private String password;
}
