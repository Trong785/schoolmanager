package com.example.schoolmanager.students.mapper;

import com.example.schoolmanager.students.dto.StudentDto;
import com.example.schoolmanager.students.dto.StudentListDto;
import com.example.schoolmanager.students.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentDto dto) {
        if (dto == null) return null;
        return Student.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    public StudentDto toDto(Student entity) {
        if (entity == null) return null;
        return StudentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }

    public StudentListDto toListDto(Student entity) {
        if (entity == null) return null;
        return StudentListDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}