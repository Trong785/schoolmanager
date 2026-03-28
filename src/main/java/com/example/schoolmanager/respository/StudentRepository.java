package com.example.schoolmanager.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.schoolmanager.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentRepository extends JpaRepository<Student, Integer> {

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