package com.example.todolistapp.service;

import com.example.todolistapp.model.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    Task getTaskById(Long taskId);

    List<Task> getAllTasks();

    Task updateTaskById(Long taskId, Task task);

    void deleteTaskById(Long taskId);

}
