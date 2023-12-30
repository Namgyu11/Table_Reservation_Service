package com.example.table_reservation_service.manager.repository;

import com.example.table_reservation_service.manager.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByEmail(String email);
    boolean existsByEmail(String email);
}
