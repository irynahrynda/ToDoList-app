package com.example.todolistapp.repository;

import com.example.todolistapp.model.TasksList;
import com.example.todolistapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TasksListRepository extends JpaRepository<TasksList, Long> {

    List<TasksList> findByUserEmail(String userEmail);
}
