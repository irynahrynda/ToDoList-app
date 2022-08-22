package com.example.todolistapp.controller;

import com.example.todolistapp.dto.request.TaskRequestDto;
import com.example.todolistapp.dto.response.TaskResponseDto;
import com.example.todolistapp.mapper.TaskMapper;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping("/taskslists/{id}")
    public TaskResponseDto createTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskService.createTask(id, taskMapper.mapToModel(taskRequestDto));
        return taskMapper.mapToDto(task);
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return taskMapper.mapToDto(taskService.getTaskById(id));
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks().stream()
                .map(taskMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTask(@PathVariable Long id,
                                      @RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskService.updateTaskById(id, taskMapper.mapToModel(taskRequestDto));
        return taskMapper.mapToDto(task);
    }

    @DeleteMapping("/{id}")
    public String deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "Task by id " + id + " was deleted";
    }
}
