package com.example.todolistapp.controller;

import com.example.todolistapp.dto.request.TasksListRequestDto;
import com.example.todolistapp.dto.response.TasksListResponseDto;
import com.example.todolistapp.mapper.TasksListMapper;
import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.service.TasksListService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/taskslists")
public class TasksListController {
    private final TasksListService tasksListService;
    private final TasksListMapper tasksListMapper;

    public TasksListController(TasksListService tasksListService, TasksListMapper tasksListMapper) {
        this.tasksListService = tasksListService;
        this.tasksListMapper = tasksListMapper;
    }

    @PostMapping
    public TasksListResponseDto createTasksList(@RequestBody TasksListRequestDto tasksListRequestDto) {
        TasksList tasksList = tasksListService.createTasksList(tasksListMapper.mapToModel(tasksListRequestDto));
        return tasksListMapper.mapToDto(tasksList);
    }

    @GetMapping("/{id}")
    public TasksListResponseDto getTasksListById(@PathVariable Long id) {
        return tasksListMapper.mapToDto(tasksListService.getTasksListById(id));
    }

    @GetMapping
    public List<TasksListResponseDto> getAllTasksLists() {
        return tasksListService.getAllTasksList().stream()
                .map(tasksListMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public TasksListResponseDto updateTasksList(@PathVariable Long id,
                                      @RequestBody TasksListRequestDto tasksListRequestDto) {
        TasksList tasksList = tasksListService.updateTasksListById(id, tasksListMapper.mapToModel(tasksListRequestDto));
        return tasksListMapper.mapToDto(tasksList);
    }

    @DeleteMapping("/{id}")
    public String deleteTasksListById(@PathVariable Long id) {
      tasksListService.deleteTasksListById(id);
        return "TasksList by id " + id + " was deleted";
    }
}
