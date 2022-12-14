package com.example.todolistapp.controller;

import com.example.todolistapp.dto.request.TasksListRequestDto;
import com.example.todolistapp.dto.response.TasksListResponseDto;
import com.example.todolistapp.mapper.TasksListMapper;
import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.service.TasksListService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskslists")
public class TasksListController {
    private final TasksListService tasksListService;
    private final TasksListMapper tasksListMapper;

    public TasksListController(TasksListService tasksListService, TasksListMapper tasksListMapper) {
        this.tasksListService = tasksListService;
        this.tasksListMapper = tasksListMapper;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    @ApiOperation(value = "Create taskslist by id")
    public TasksListResponseDto createTasksList(
            @RequestBody @Valid TasksListRequestDto tasksListRequestDto) {
        TasksList tasksList = tasksListService.createTasksList(
                tasksListMapper.mapToModel(tasksListRequestDto));
        return tasksListMapper.mapToDto(tasksList);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Get taskslist by id")
    public TasksListResponseDto getTasksListById(@PathVariable Long id) {
        return tasksListMapper.mapToDto(tasksListService.getTasksListById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    @ApiOperation(value = "Get all taskslist with pagination")
    public List<TasksListResponseDto> getAllUsersTasksLists(
            @RequestParam(defaultValue = "10")
            @ApiParam(value = "Default value " + "is `10`")
            Integer count,
            @RequestParam(defaultValue = "0")
            @ApiParam(value = "Default value " + "is `0`")
            Integer page) {
        PageRequest pageRequest = PageRequest.of(page, count);
        return tasksListService.getAllTasksLists(pageRequest)
                .stream()
                .map(tasksListMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/{id}")
    @ApiOperation(value = "Update taskslist by id")
    public TasksListResponseDto updateTasksList(@PathVariable Long id,
                                                @RequestBody
                                                @Valid TasksListRequestDto tasksListRequestDto) {
        TasksList tasksList = tasksListService.updateTasksListById(id,
                tasksListMapper.mapToModel(tasksListRequestDto));
        return tasksListMapper.mapToDto(tasksList);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete taskslist by id")
    public String deleteTasksListById(@PathVariable Long id) {
        tasksListService.deleteTasksListById(id);
        return "TasksList by id " + id + " was deleted";
    }
}
