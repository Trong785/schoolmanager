package com.example.schoolmanager.service;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.respository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAll() {
        return repo.findAll();
    }

    public Student getById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public Student create(Student student) {
        return repo.save(student);   // UUID tự sinh ở đây
    }

    public Student update(UUID id, Student student) {
        Student old = getById(id);
        if (old == null) return null;

        old.setName(student.getName());
        old.setEmail(student.getEmail());
        old.setGender(student.getGender());

        return repo.save(old);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }

    public Page<Student> search(String keyword, int page, int size) {
        String k = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);
        if (k.isEmpty()) {
            return repo.findAll(pageable);
        }
        return repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(k, k, pageable);
    }

    public Page<Student> searchByName(String name, int page, int size) {
        String n = (name == null) ? "" : name.trim();
        Pageable pageable = PageRequest.of(page, size);
        if (n.isEmpty()) {
            return repo.findAll(pageable);
        }
        return repo.findByNameContainingIgnoreCase(n, pageable);
    }
}