package com.example.todolistapp.repository;

import com.example.todolistapp.model.Task;
import com.example.todolistapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
