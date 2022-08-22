package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Role;
import com.example.todolistapp.repository.RoleRepository;
import com.example.todolistapp.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findAllByRoleName(String roleName) {
        return roleRepository.findAllByRoleName(roleName).orElseThrow(
                () -> new RuntimeException("Can't get role by role name: " + roleName));
    }
}
