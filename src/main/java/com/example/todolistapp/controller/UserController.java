package com.example.todolistapp.controller;

import com.example.todolistapp.dto.request.TaskRequestDto;
import com.example.todolistapp.dto.request.UserRequestDto;
import com.example.todolistapp.dto.response.TaskResponseDto;
import com.example.todolistapp.dto.response.UserResponseDto;
import com.example.todolistapp.mapper.UserMapper;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.model.User;
import com.example.todolistapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.createUser(userMapper.mapToModel(userRequestDto));
        return userMapper.mapToDto(user);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userMapper.mapToDto(userService.getUserById(id));
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
      return userService.getAllUsers().stream()
              .map(userMapper::mapToDto)
              .collect(Collectors.toList());
    }


    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id,
                                      @RequestBody UserRequestDto userRequestDto) {
        User user = userService.updateUserById(id, userMapper.mapToModel(userRequestDto));
        return userMapper.mapToDto(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
       userService.deleteUserById(id);
        return "User by id " + id + " was deleted";
    }

}
