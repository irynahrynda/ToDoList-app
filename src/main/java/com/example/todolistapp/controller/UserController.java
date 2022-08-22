package com.example.todolistapp.controller;

import com.example.todolistapp.dto.request.UserRequestDto;
import com.example.todolistapp.dto.response.UserResponseDto;
import com.example.todolistapp.mapper.UserMapper;
import com.example.todolistapp.model.User;
import com.example.todolistapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation(value = "Create new user")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.createUser(userMapper.mapToModel(userRequestDto));
        return userMapper.mapToDto(user);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userMapper.mapToDto(userService.getUserById(id));
    }

    @GetMapping
    @ApiOperation(value = "Get all users with pagination")
    public List<UserResponseDto> getAllUsers(@RequestParam(defaultValue = "10")
                                             @ApiParam(value = "Default value " + "is `10`") Integer count,
                                             @RequestParam(defaultValue = "0")
                                             @ApiParam(value = "Default value " + "is `0`") Integer page) {
        PageRequest pageRequest = PageRequest.of(page, count);
        return userService.getAllUsers().stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user by id")
    public UserResponseDto updateUser(@PathVariable Long id,
                                      @RequestBody UserRequestDto userRequestDto) {
        User user = userService.updateUserById(id, userMapper.mapToModel(userRequestDto));
        return userMapper.mapToDto(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by id")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User by id " + id + " was deleted";
    }
}
