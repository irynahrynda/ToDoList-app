package com.example.todolistapp.service;

import com.example.todolistapp.model.Role;

public interface RoleService {
    Role save(Role role);

    Role findAllByRoleName(String roleName);
}
