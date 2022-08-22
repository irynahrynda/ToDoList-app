package com.example.todolistapp.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class TasksListResponseDto {
    private Long id;
    private String name;
    private StatusResponseDto status;
    private LocalDateTime deadline;
    private List<TaskResponseDto> tasks;
    private UserResponseDto user;
}
