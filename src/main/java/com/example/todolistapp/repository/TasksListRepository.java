package com.example.todolistapp.repository;

import com.example.todolistapp.model.TasksList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksListRepository extends JpaRepository<TasksList, Long> {
}
