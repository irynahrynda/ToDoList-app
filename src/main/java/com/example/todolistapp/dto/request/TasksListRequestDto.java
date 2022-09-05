package com.example.todolistapp.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class TasksListRequestDto {
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 30)
    private String name;
    private LocalDateTime deadline;
    @Positive
    private Long statusId;
    @Positive
    private Long priorityId;
}
