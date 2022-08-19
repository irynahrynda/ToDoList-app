package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.request.TaskRequestDto;
import com.example.todolistapp.dto.response.TaskResponseDto;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.service.StatusService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final StatusMapper statusMapper;
    private StatusService statusService;

    public TaskMapper(StatusMapper statusMapper, StatusService statusService) {
        this.statusMapper = statusMapper;
        this.statusService = statusService;
    }

    public TaskResponseDto mapToDto(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setName(task.getName());
        if (task.getStatus() != null) {
            taskResponseDto.setStatus(statusMapper.mapToDto(task.getStatus()));
        }
        taskResponseDto.setFinishDate(task.getFinishDate());
        return taskResponseDto;
    }

    public Task mapToModel(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setName(taskRequestDto.getName());
        if (taskRequestDto.getStatusId() != null) {
            task.setStatus(statusService.getStatusById(taskRequestDto.getStatusId()));
        }
        return task;
    }
}
