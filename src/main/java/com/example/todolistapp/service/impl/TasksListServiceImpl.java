package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Status;
import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.repository.TasksListRepository;
import com.example.todolistapp.service.StatusService;
import com.example.todolistapp.service.TasksListService;
import java.util.List;

import com.example.todolistapp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TasksListServiceImpl implements TasksListService {
    private final TasksListRepository tasksListRepository;
    private final StatusService statusService;
    private final UserService userService;

    public TasksListServiceImpl(TasksListRepository tasksListRepository, StatusService statusService, UserService userService) {
        this.tasksListRepository = tasksListRepository;
        this.statusService = statusService;
        this.userService = userService;
    }

    @Override
    public TasksList createTasksList(TasksList tasksList) {
        if (tasksList.getStatus() == null) {
            tasksList.setStatus(statusService.getStatusByName(Status.StatusName.TO_DO));
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        tasksList.setUser(userService.getUserByEmail(currentPrincipalName));

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

        if (tasksList.getDeadline() != null) {
            tasksListToUpdate.setDeadline(tasksList.getDeadline());
        }

        if (tasksList.getStatus() != null) {
            tasksListToUpdate.setStatus(tasksList.getStatus());
        }

        createTasksList(tasksListToUpdate);

        return createTasksList(tasksListToUpdate);
    }

    @Override
    public void deleteTasksListById(Long tasksListId) {
        tasksListRepository.delete(getTasksListById(tasksListId));
    }
}
