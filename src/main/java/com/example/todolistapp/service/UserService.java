package com.example.todolistapp.service;

import com.example.todolistapp.model.User;

import java.util.List;

public interface UserService {
    User createUser (User user);
    User getUserById (Long userId);
    List<User> getAllUsers();
    User updateUserById (Long userId, User user);
    void deleteUserById (Long userId);

}