package com.example.todolistapp.service;

import com.example.todolistapp.model.TasksList;
import java.util.List;

public interface TasksListService {
    TasksList createTasksList(TasksList tasksList);

    TasksList getTasksListById(Long tasksListId);

    List<TasksList> getAllTasksList();

    TasksList updateTasksListById(Long tasksListId, TasksList tasksList);

    void deleteTasksListById(Long tasksListId);
}
