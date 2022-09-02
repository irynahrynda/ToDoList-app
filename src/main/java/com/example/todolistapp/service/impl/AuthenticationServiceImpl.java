package com.example.todolistapp.service.impl;

import com.example.todolistapp.exception.AuthenticationException;
import com.example.todolistapp.model.Role;
import com.example.todolistapp.model.User;
import com.example.todolistapp.repository.UserRepository;
import com.example.todolistapp.service.AuthenticationService;
import com.example.todolistapp.service.RoleService;
import com.example.todolistapp.service.UserService;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserRepository userRepository, UserService userService,
                                     RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String email, String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.findAllByRoleName(Role.RoleName.USER.name())));
        userService.createUser(user);
        return user;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Incorrect username or password!"));

        if (user.getEmail().equals(email)
                && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new AuthenticationException("Incorrect username or password!");
        }
    }
}
