package com.example.todolistapp.service;

import com.example.todolistapp.exception.AuthenticationException;
import com.example.todolistapp.model.User;

public interface AuthenticationService {
    User register(String email, String password);

    User login(String email, String password) throws AuthenticationException;
}
