package com.example.todolistapp.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TasksListRequestDto {
    private String name;
    private LocalDateTime deadline;
    private Long statusId;
}
