package com.example.todolistapp.service;

import com.example.todolistapp.model.Task;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface TaskService {
    Task createTask(Long tasksListId, Task task);

    Task getTaskById(Long taskId);

    List<Task> getAllTasks(PageRequest pageRequest);

    Task updateTaskById(Long taskId, Task task);

    void deleteTaskById(Long taskId);
}
