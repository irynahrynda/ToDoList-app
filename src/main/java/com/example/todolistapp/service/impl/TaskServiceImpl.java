package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Status;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.repository.TaskRepository;
import com.example.todolistapp.repository.TasksListRepository;
import com.example.todolistapp.service.StatusService;
import com.example.todolistapp.service.TaskService;
import com.example.todolistapp.service.TasksListService;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final StatusService statusService;
    private final TasksListService tasksListService;
    private final TasksListRepository tasksListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, StatusService statusService,
                           TasksListService tasksListService, TasksListRepository tasksListRepository) {
        this.taskRepository = taskRepository;
        this.statusService = statusService;
        this.tasksListService = tasksListService;
        this.tasksListRepository = tasksListRepository;
    }

    @Transactional
    @Override
    public Task createTask(Long tasksListId, Task task) {
        TasksList tasksList = tasksListService.getTasksListById(tasksListId);
        if (!tasksList.getStatus().getStatusName().equals(Status.StatusName.DONE)) {
            task.setTasksList(tasksList);
        } else {
            throw new RuntimeException("Cant add task to tasksList");
        }

        if (task.getStatus().getStatusName().equals(Status.StatusName.DONE)) {
            task.setStatus(statusService.getStatusByName(Status.StatusName.IN_PROGRESS));
            System.out.println("Can't create task  with status \"DONE\", task has status \"IN PROGRESS\"");
        }

        if (task.getStatus() == null) {
            task.setStatus(statusService.getStatusByName(Status.StatusName.TO_DO));
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Task createdTask = new Task();
        if (tasksList.getUser().getEmail().equals(currentPrincipalName)) {
             createdTask = taskRepository.save(task);
        } else {
            throw new RuntimeException("You can't add task to this taskslist");
        }

        if (createdTask != null) {
            List<Task> tasks = tasksList.getTasks();
            tasks.add(createdTask);
            tasksList.setTasks(tasks);
            tasksListRepository.save(tasksList);
        }

        updateStatusTasksList(createdTask);
        return createdTask;
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

    @Transactional
    @Override
    public Task updateTaskById(Long taskId, Task task) {
        Task taskToUpdate = getTaskById(taskId);
        if (task.getName() != null) {
            taskToUpdate.setName(task.getName());
        }

        if (task.getStatus() != null) {
            taskToUpdate.setStatus(task.getStatus());
        }
        updateStatusTasksList(taskToUpdate);
        return taskRepository.save(taskToUpdate);
    }

    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.delete(getTaskById(taskId));
    }

    private void updateStatusTasksList(Task taskToUpdate) {
        TasksList tasksList = taskToUpdate.getTasksList();
        Long tasksCounter = tasksList.getCounter();

        if (!tasksList.getStatus().getStatusName().equals(taskToUpdate.getStatus().getStatusName())
                && taskToUpdate.getStatus().getId() > tasksList.getStatus().getId()
                && !taskToUpdate.getStatus().getStatusName().equals(Status.StatusName.DONE)) {
            tasksList.setStatus(taskToUpdate.getStatus());
        }

        if (!tasksList.getStatus().getStatusName().equals(Status.StatusName.DONE)
                && !tasksList.getStatus().getStatusName().equals(Status.StatusName.IN_PROGRESS)
                && taskToUpdate.getStatus().getStatusName().equals(Status.StatusName.DONE)) {
            tasksList.setStatus(statusService.getStatusByName(Status.StatusName.IN_PROGRESS));
        }

        if (taskToUpdate.getStatus().getStatusName().equals(Status.StatusName.DONE)) {
            taskToUpdate.setFinishDate(LocalDateTime.now());
            tasksCounter++;

            tasksList.setCounter(tasksCounter);
        }

        if (tasksCounter == tasksList.getTasks().size()) {
            if (tasksList.getTasks().stream()
                    .map(Task::getFinishDate)
                    .filter(e -> e.isAfter(tasksList.getDeadline()))
                    .count() > 0) {
                tasksList.setStatus(statusService.getStatusByName(Status.StatusName.TERMINATED));
            } else {
                tasksList.setStatus(statusService.getStatusByName(Status.StatusName.DONE));
            }
        }
        tasksListRepository.save(tasksList);
    }
}
