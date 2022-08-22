package com.example.todolistapp.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    List<Long> rolesIds;
}
