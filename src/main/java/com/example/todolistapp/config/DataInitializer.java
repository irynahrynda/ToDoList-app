package com.example.todolistapp.config;

import com.example.todolistapp.model.Priority;
import com.example.todolistapp.model.Role;
import com.example.todolistapp.model.Status;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.model.User;
import com.example.todolistapp.repository.RoleRepository;
import com.example.todolistapp.repository.TaskRepository;
import com.example.todolistapp.repository.TasksListRepository;
import com.example.todolistapp.service.PriorityService;
import com.example.todolistapp.service.StatusService;
import com.example.todolistapp.service.UserService;
import java.time.LocalDateTime;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final StatusService statusService;
    private final TasksListRepository tasksListRepository;
    private final TaskRepository taskRepository;
    private final PriorityService priorityService;

    public DataInitializer(RoleRepository roleRepository, UserService userService,
                           StatusService statusService, TasksListRepository tasksListRepository,
                           TaskRepository taskRepository, PriorityService priorityService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.statusService = statusService;
        this.tasksListRepository = tasksListRepository;
        this.taskRepository = taskRepository;
        this.priorityService = priorityService;
    }

    @PostConstruct
    public void inject() {
        Status toDoStatus = new Status(Status.StatusName.TO_DO);
        statusService.createStatus(toDoStatus);
        Status inProgressStatus = new Status(Status.StatusName.IN_PROGRESS);
        statusService.createStatus(inProgressStatus);
        Status doneStatus = new Status(Status.StatusName.DONE);
        statusService.createStatus(doneStatus);
        Status terminatedStatus = new Status(Status.StatusName.TERMINATED);
        statusService.createStatus(terminatedStatus);

        Priority lowPriority = new Priority();
        lowPriority.setPriorityName(Priority.PriorityName.LOW);
        priorityService.createPriority(lowPriority);
        Priority mediumPriority = new Priority();
        mediumPriority.setPriorityName(Priority.PriorityName.MEDIUM);
        priorityService.createPriority(mediumPriority);
        Priority highPriority = new Priority();
        highPriority.setPriorityName(Priority.PriorityName.HIGH);
        priorityService.createPriority(highPriority);

        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleRepository.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleRepository.save(userRole);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin123");
        admin.setName("AdminIra");
        admin.setRoles(Set.of(adminRole));
        userService.createUser(admin);

        User user1 = new User();
        user1.setEmail("userBob@gmail.com");
        user1.setPassword("bob12345");
        user1.setName("Bob");
        user1.setRoles(Set.of(userRole));
        userService.createUser(user1);

        User user2 = new User();
        user2.setEmail("userAlice@gmail.com");
        user2.setPassword("alice12345");
        user2.setName("Alice");
        user2.setRoles(Set.of(userRole));
        userService.createUser(user2);

        TasksList tasksList1 = new TasksList();
        tasksList1.setName("Tasklist1Bob");
        tasksList1.setStatus(toDoStatus);
        tasksList1.setDeadline(LocalDateTime.now().plusDays(5));
        tasksList1.setPriority(mediumPriority);
        tasksList1.setUser(user1);
        tasksListRepository.save(tasksList1);

        TasksList tasksList2 = new TasksList();
        tasksList2.setName("TaskList2Alice");
        tasksList2.setStatus(toDoStatus);
        tasksList2.setDeadline(LocalDateTime.now().plusDays(8));
        tasksList2.setPriority(highPriority);
        tasksList2.setUser(user2);
        tasksListRepository.save(tasksList2);

        Task task1 = new Task();
        task1.setName("task1BuyProductsBob");
        task1.setStatus(toDoStatus);
        task1.setPriority(lowPriority);
        task1.setTasksList(tasksList1);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setName("task2CleanRoomBob");
        task2.setStatus(toDoStatus);
        task2.setPriority(mediumPriority);
        task2.setTasksList(tasksList1);
        taskRepository.save(task2);

        Task task3 = new Task();
        task3.setName("task3VisitRelativesAlice");
        task3.setStatus(toDoStatus);
        task3.setPriority(mediumPriority);
        task3.setTasksList(tasksList2);
        taskRepository.save(task3);

        Task task4 = new Task();
        task4.setName("task4BakeACakeAlice");
        task4.setStatus(toDoStatus);
        task4.setPriority(highPriority);
        task4.setTasksList(tasksList2);
        taskRepository.save(task4);
    }
}
