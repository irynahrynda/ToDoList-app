package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.request.TasksListRequestDto;
import com.example.todolistapp.dto.response.TasksListResponseDto;
import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.service.StatusService;
import org.springframework.stereotype.Component;

@Component
public class TasksListMapper {
    private final UserMapper userMapper;
   private final StatusMapper statusMapper;
    private StatusService statusService;

    public TasksListMapper(UserMapper userMapper, StatusMapper statusMapper, StatusService statusService) {
        this.userMapper = userMapper;
        this.statusMapper = statusMapper;
        this.statusService = statusService;
    }


    public TasksListResponseDto mapToDto(TasksList tasksList) {
        TasksListResponseDto tasksListResponseDto = new TasksListResponseDto();
        tasksListResponseDto.setId(tasksList.getId());
        tasksListResponseDto.setName(tasksList.getName());
        if (tasksList.getStatus() != null) {
            tasksListResponseDto.setStatus(statusMapper.mapToDto(tasksList.getStatus()));
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
        return tasksList;
    }
}
