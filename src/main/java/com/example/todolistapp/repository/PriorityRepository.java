package com.example.todolistapp.repository;

import com.example.todolistapp.model.Priority;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Optional<Priority> findByPriorityName(Priority.PriorityName priorityName);
}
