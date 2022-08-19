package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.User;
import com.example.todolistapp.repository.UserRepository;
import com.example.todolistapp.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("Can't find user by id " + userId));
    }

    @Override
    public List<User> getAllUsers() {
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
}
