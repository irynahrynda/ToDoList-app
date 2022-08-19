package com.example.todolistapp.repository;

import com.example.todolistapp.model.Status;
import com.example.todolistapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
