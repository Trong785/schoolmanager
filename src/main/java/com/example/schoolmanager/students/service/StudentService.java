package com.example.schoolmanager.students.service;

import com.example.schoolmanager.students.dto.StudentDto;
import com.example.schoolmanager.students.dto.StudentListDto;
import com.example.schoolmanager.students.entity.Student;
import com.example.schoolmanager.students.mapper.StudentMapper;
import com.example.schoolmanager.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Page<StudentListDto> search(String keyword, Pageable pageable) {
        Page<Student> page = studentRepository.search(
                keyword != null ? keyword.trim() : "", pageable);
        List<StudentListDto> dtos = page.getContent().stream()
                .map(studentMapper::toListDto)
                .toList();
        return new PageImpl<>(dtos, page.getPageable(), page.getTotalElements());
    }

    public Optional<StudentDto> findByIdAsDto(UUID id) {
        return studentRepository.findById(id).map(studentMapper::toDto);
    }

    public Optional<Student> findByEmail(String email) {
        return email == null ? Optional.empty() : studentRepository.findByEmail(email);
    }

    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }
}