package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.repository.TasksListRepository;
import com.example.todolistapp.service.TasksListService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TasksListServiceImpl implements TasksListService {
    private final TasksListRepository tasksListRepository;

    public TasksListServiceImpl(TasksListRepository tasksListRepository) {
        this.tasksListRepository = tasksListRepository;
    }

    @Override
    public TasksList createTasksList(TasksList tasksList) {
        return tasksListRepository.save(tasksList);
    }

    @Override
    public TasksList getTasksListById(Long tasksListId) {
        return tasksListRepository.findById(tasksListId).orElseThrow(() ->
                new RuntimeException("Can't find tasksListId by id " + tasksListId));
    }

    @Override
    public List<TasksList> getAllTasksList() {
        return tasksListRepository.findAll();
    }

    @Override
    public TasksList updateTasksListById(Long tasksListId, TasksList tasksList) {
        TasksList tasksListToUpdate = getTasksListById(tasksListId);
        if (tasksList.getName() != null) {
            tasksListToUpdate.setName(tasksList.getName());
        }

        if (tasksList.getStatus() != null) {
            tasksListToUpdate.setStatus(tasksList.getStatus());
        }

        return createTasksList(tasksListToUpdate);
    }

    @Override
    public void deleteTasksListById(Long tasksListId) {
        tasksListRepository.delete(getTasksListById(tasksListId));
    }
}
