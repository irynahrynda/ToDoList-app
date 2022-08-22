package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Role;
import com.example.todolistapp.model.User;
import com.example.todolistapp.service.AuthenticationService;
import com.example.todolistapp.service.RoleService;
import com.example.todolistapp.service.UserService;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.findAllByRoleName(Role.RoleName.USER.name())));
        userService.createUser(user);
        return user;
    }
}
