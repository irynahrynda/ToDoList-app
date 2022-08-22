package com.example.todolistapp.repository;

import com.example.todolistapp.model.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByStatusName(Status.StatusName statusName);
}
