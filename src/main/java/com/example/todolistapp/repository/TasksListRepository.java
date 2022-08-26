package com.example.todolistapp.repository;

import com.example.todolistapp.model.TasksList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksListRepository extends JpaRepository<TasksList, Long> {
    List<TasksList> findByUserEmail(String userEmail);

    @Query(nativeQuery = true,
            value = "SELECT * "
                  + "FROM taskslists "
                  + "WHERE id = ? AND user_id = ?")
    Optional<TasksList> findByIdAndUserName(Long taskslistId, Long userId);
}
