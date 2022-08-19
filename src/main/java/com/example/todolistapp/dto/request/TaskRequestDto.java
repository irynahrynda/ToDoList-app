package com.example.todolistapp.dto.request;

import lombok.Data;

@Data
public class TaskRequestDto {
    private String name;
    private Long statusId;
}
