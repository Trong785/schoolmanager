package com.example.schoolmanager.students.repository;

import com.example.schoolmanager.students.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByEmail(String email);

    @Query("""
           SELECT s FROM Student s
           WHERE
             (:keyword IS NULL OR :keyword = '' OR
               LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
               LOWER(COALESCE(s.email, '')) LIKE LOWER(CONCAT('%', :keyword, '%')))
           """)
    Page<Student> search(@Param("keyword") String keyword, Pageable pageable);
}