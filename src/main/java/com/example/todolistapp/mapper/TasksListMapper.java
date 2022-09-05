package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.request.TasksListRequestDto;
import com.example.todolistapp.dto.response.TasksListResponseDto;
import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.service.PriorityService;
import com.example.todolistapp.service.StatusService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TasksListMapper {
    private final UserMapper userMapper;
    private final StatusMapper statusMapper;
    private final StatusService statusService;
    private final TaskMapper taskMapper;
    private final PriorityService priorityService;
    private final PriorityMapper priorityMapper;

    public TasksListMapper(UserMapper userMapper, StatusMapper statusMapper,
                           StatusService statusService, TaskMapper taskMapper,
                           PriorityService priorityService, PriorityMapper priorityMapper) {
        this.userMapper = userMapper;
        this.statusMapper = statusMapper;
        this.statusService = statusService;
        this.taskMapper = taskMapper;
        this.priorityService = priorityService;
        this.priorityMapper = priorityMapper;
    }

    public TasksListResponseDto mapToDto(TasksList tasksList) {
        TasksListResponseDto tasksListResponseDto = new TasksListResponseDto();
        tasksListResponseDto.setId(tasksList.getId());
        tasksListResponseDto.setName(tasksList.getName());
        if (tasksList.getStatus() != null) {
            tasksListResponseDto.setStatus(statusMapper.mapToDto(tasksList.getStatus()));
        }

        if (tasksList.getPriority() != null) {
            tasksListResponseDto.setPriority(priorityMapper.mapToDto(tasksList.getPriority()));
        }

        if (tasksList.getTasks() != null) {
            tasksListResponseDto.setTasks(tasksList.getTasks().stream()
                    .map(taskMapper::mapToDto)
                    .collect(Collectors.toList()));
        }

        tasksListResponseDto.setDeadline(tasksList.getDeadline());

        if (tasksList.getUser() != null) {
            tasksListResponseDto.setUser(userMapper.mapToDto(tasksList.getUser()));
        }
        return tasksListResponseDto;
    }

    public TasksList mapToModel(TasksListRequestDto tasksListRequestDto) {
        TasksList tasksList = new TasksList();
        tasksList.setName(tasksListRequestDto.getName());
        if (tasksListRequestDto.getStatusId() != null) {
            tasksList.setStatus(statusService.getStatusById(tasksListRequestDto.getStatusId()));
        }

        if (tasksListRequestDto.getPriorityId() != null) {
            tasksList.setPriority(priorityService
                    .getPriorityById(tasksListRequestDto.getPriorityId()));
        }

        if (tasksListRequestDto.getDeadline() != null) {
            tasksList.setDeadline(tasksListRequestDto.getDeadline());
        }
        return tasksList;
    }
}
