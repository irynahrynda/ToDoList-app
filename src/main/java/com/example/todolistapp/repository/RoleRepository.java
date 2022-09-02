package com.example.todolistapp.repository;

import com.example.todolistapp.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT * "
            + "FROM roles "
            + "WHERE role_name = ?", nativeQuery = true)
    Optional<Role> findAllByRoleName(String roleName);
}
