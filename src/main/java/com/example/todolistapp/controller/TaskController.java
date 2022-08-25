package com.example.todolistapp.controller;

import com.example.todolistapp.dto.request.TaskRequestDto;
import com.example.todolistapp.dto.response.TaskResponseDto;
import com.example.todolistapp.mapper.TaskMapper;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    @ApiOperation(value = "Create new task")
    public TaskResponseDto createTask(@PathVariable Long id,
                                      @RequestBody @Valid TaskRequestDto taskRequestDto) {
        Task task = taskService.createTask(id, taskMapper.mapToModel(taskRequestDto));
        return taskMapper.mapToDto(task);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get task by id")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return taskMapper.mapToDto(taskService.getTaskById(id));
    }

    @GetMapping
    @ApiOperation(value = "Get all tasks with pagination")
    public List<TaskResponseDto> getAllTasks(@RequestParam(defaultValue = "10")
                                             @ApiParam(value = "Default value " + "is `10`") Integer count,
                                             @RequestParam(defaultValue = "0")
                                             @ApiParam(value = "Default value " + "is `0`") Integer page) {
        PageRequest pageRequest = PageRequest.of(page, count);
        return taskService.getAllTasks().stream()
                .map(taskMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update task by id")
    public TaskResponseDto updateTask(@PathVariable Long id,
                                      @RequestBody @Valid TaskRequestDto taskRequestDto) {
        Task task = taskService.updateTaskById(id, taskMapper.mapToModel(taskRequestDto));
        return taskMapper.mapToDto(task);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete task by id")
    public String deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "Task by id " + id + " was deleted";
    }
}
