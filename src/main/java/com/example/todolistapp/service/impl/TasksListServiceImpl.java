package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Status;
import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.model.User;
import com.example.todolistapp.repository.TasksListRepository;
import com.example.todolistapp.service.StatusService;
import com.example.todolistapp.service.TasksListService;
import com.example.todolistapp.service.UserService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TasksListServiceImpl implements TasksListService {
    private final TasksListRepository tasksListRepository;
    private final StatusService statusService;
    private final UserService userService;

    public TasksListServiceImpl(TasksListRepository tasksListRepository,
                                StatusService statusService,
                                UserService userService) {
        this.tasksListRepository = tasksListRepository;
        this.statusService = statusService;
        this.userService = userService;
    }

    @Override
    public TasksList createTasksList(TasksList tasksList) {
        if (tasksList.getStatus() == null) {
            tasksList.setStatus(statusService.getStatusByName(Status.StatusName.TO_DO));
        }

        tasksList.setUser(userService.getUserByEmail(userService.getUserEmail()).orElseThrow(
                () -> new RuntimeException("Can`t get user by email ")));
        return tasksListRepository.save(tasksList);
    }

    @Override
    public TasksList getTasksListById(Long tasksListId) {
        User user = userService.getUserByEmail(userService.getUserEmail()).orElseThrow(
                () -> new RuntimeException("Can`t get user by email "));
        if (userService.hasAdminRole(user)) {
            return tasksListRepository.findById(tasksListId).orElseThrow(() ->
                    new RuntimeException("Can't get taskslist by id " + tasksListId));
        } else {
            return tasksListRepository.findByIdAndUserName(tasksListId,
                    user.getId()).orElseThrow(() -> new RuntimeException(
                            "Can't have access to another taskslist by id " + tasksListId));
        }
    }

    @Override
    public List<TasksList> getAllTasksLists(PageRequest pageRequest) {
        User user = userService.getUserByEmail(userService.getUserEmail()).orElseThrow(
                () -> new RuntimeException("Can`t get user by email "));
        if (userService.hasAdminRole(user)) {
            return tasksListRepository.findAll();
        } else {
            return tasksListRepository.findByUserEmail(userService.getUserEmail());
        }
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

        return tasksListRepository.save(tasksListToUpdate);

    }

    @Override
    public void deleteTasksListById(Long tasksListId) {
        tasksListRepository.delete(getTasksListById(tasksListId));
    }
}
