package com.example.todolistapp.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TasksListResponseDto {
    private Long id;
    private String name;
    private StatusResponseDto status;
    private LocalDateTime deadline;
    private UserResponseDto user;
}
