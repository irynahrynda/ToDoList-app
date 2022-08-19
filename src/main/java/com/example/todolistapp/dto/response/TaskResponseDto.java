package com.example.todolistapp.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskResponseDto {
    private Long id;
    private String name;
    private StatusResponseDto status;
    private LocalDateTime finishDate;
}
