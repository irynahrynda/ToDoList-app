package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Role;
import com.example.todolistapp.model.User;
import com.example.todolistapp.repository.UserRepository;
import com.example.todolistapp.service.UserService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("Can't get user by id " + userId));
    }

    @Override
    public List<User> getAllUsers(PageRequest pageRequest) {
        return userRepository.findAll();
    }

    @Override
    public User updateUserById(Long userId, User user) {
        User userToUpdate = getUserById(userId);
        if (user.getName() != null) {
            userToUpdate.setName(user.getName());
        }

        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }

        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }

        return createUser(userToUpdate);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.delete(getUserById(userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new RuntimeException("Can`t get user by email " + email));
    }

    @Override
    public String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public boolean hasAdminRole(User user) {
        return user.getRoles().stream()
                .map(Role::getRoleName)
                .anyMatch(e -> e.equals(Role.RoleName.ADMIN));
    }
}
