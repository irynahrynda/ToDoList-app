package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.request.TaskRequestDto;
import com.example.todolistapp.dto.response.TaskResponseDto;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.service.PriorityService;
import com.example.todolistapp.service.StatusService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final StatusMapper statusMapper;
    private final StatusService statusService;
    private final PriorityService priorityService;
    private final PriorityMapper priorityMapper;

    public TaskMapper(StatusMapper statusMapper, StatusService statusService,
                      PriorityService priorityService, PriorityMapper priorityMapper) {
        this.statusMapper = statusMapper;
        this.statusService = statusService;
        this.priorityService = priorityService;
        this.priorityMapper = priorityMapper;
    }

    public TaskResponseDto mapToDto(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setName(task.getName());
        if (task.getStatus() != null) {
            taskResponseDto.setStatus(statusMapper.mapToDto(task.getStatus()));
        }

        if (task.getPriority() != null) {
            taskResponseDto.setPriority(priorityMapper.mapToDto(task.getPriority()));
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

        if (taskRequestDto.getPriorityId() != null) {
            task.setPriority(priorityService.getPriorityById(taskRequestDto.getPriorityId()));
        }
        return task;
    }
}
