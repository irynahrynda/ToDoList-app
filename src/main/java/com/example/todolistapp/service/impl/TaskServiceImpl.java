package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Task;
import com.example.todolistapp.repository.TaskRepository;
import com.example.todolistapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() ->
                new RuntimeException("Can't find task by id " + taskId));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTaskById(Long taskId, Task task) {
        Task taskToUpdate = getTaskById(taskId);
        if (task.getName() != null) {
            taskToUpdate.setName(task.getName());
        }

        if (task.getStatus() != null) {
            taskToUpdate.setStatus(task.getStatus());
        }

        return createTask(taskToUpdate);
    }

    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.delete(getTaskById(taskId));
    }
}
