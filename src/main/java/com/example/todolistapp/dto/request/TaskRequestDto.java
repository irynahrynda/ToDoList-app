package com.example.todolistapp.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequestDto {
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 30)
    private String name;
    @Positive
    private Long statusId;
}
