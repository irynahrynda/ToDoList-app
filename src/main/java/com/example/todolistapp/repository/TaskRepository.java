package com.example.todolistapp.repository;

import com.example.todolistapp.model.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * "
                    + "FROM tasks "
                    + "INNER JOIN taskslists "
                    + "ON tasks.tasks_list_id = taskslists.id "
                    + "WHERE tasks.id = ? AND taskslists.user_id = ? ")
    Optional<Task> findByIdAndUserName(Long taskId, Long userId);

    @Query(nativeQuery = true,
            value = "SELECT * "
                    + "FROM tasks "
                    + "INNER JOIN taskslists "
                    + "ON tasks.tasks_list_id = taskslists.id "
                    + "WHERE taskslists.user_id = ? ")
    List<Task> findAllByUserId(Long userId);
}
