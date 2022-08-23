package com.example.todolistapp.config;

import com.example.todolistapp.model.Role;
import com.example.todolistapp.model.Status;
import com.example.todolistapp.model.User;
import com.example.todolistapp.service.RoleService;
import com.example.todolistapp.service.StatusService;
import com.example.todolistapp.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;
    private final StatusService statusService;

    public DataInitializer(RoleService roleService, UserService userService,
                           StatusService statusService) {
        this.roleService = roleService;
        this.userService = userService;
        this.statusService = statusService;
    }

    @PostConstruct
    public void injectStatuses() {
        Status toDoStatus = new Status(Status.StatusName.TO_DO);
        statusService.createStatus(toDoStatus);
        Status inProgressStatus = new Status(Status.StatusName.IN_PROGRESS);
        statusService.createStatus(inProgressStatus);
        Status doneStatus = new Status(Status.StatusName.DONE);
        statusService.createStatus(doneStatus);
        Status terminatedStatus = new Status(Status.StatusName.TERMINATED);
        statusService.createStatus(terminatedStatus);
    }

    @PostConstruct
    public void injectRoles() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.save(userRole);
        User admin = new User();
        admin.setEmail("admin@i.ua");
        admin.setPassword("admin123");
        admin.setRoles(Set.of(adminRole));
        userService.createUser(admin);
        User user = new User();
        user.setEmail("user@i.ua");
        user.setPassword("user123");
        user.setRoles(Set.of(userRole));
        userService.createUser(user);
    }
}
