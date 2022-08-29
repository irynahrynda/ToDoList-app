package com.example.todolistapp.service;

import com.example.todolistapp.model.User;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers(PageRequest pageRequest);

    User updateUserById(Long userId, User user);

    void deleteUserById(Long userId);

    User getUserByEmail(String email);

    String getUserEmail();

    boolean hasAdminRole(User user);
}
