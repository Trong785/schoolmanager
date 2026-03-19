package com.example.schoolmanager.respository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.schoolmanager.model.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    Page<Student> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name,
            String email,
            Pageable pageable
    );

    Page<Student> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}