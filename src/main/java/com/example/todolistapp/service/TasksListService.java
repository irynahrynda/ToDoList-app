package com.example.todolistapp.service;

import com.example.todolistapp.model.TasksList;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface TasksListService {
    TasksList createTasksList(TasksList tasksList);

    TasksList getTasksListById(Long tasksListId);

    List<TasksList> getAllTasksLists(PageRequest pageRequest);

    TasksList updateTasksListById(Long tasksListId, TasksList tasksList);

    void deleteTasksListById(Long tasksListId);
}
