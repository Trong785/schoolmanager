package com.example.schoolmanager.service;
import java.util.List;
import com.example.schoolmanager.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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

    public Student getById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Student create(Student student) {
        return repo.save(student);
    }

    public Student update(int id, Student student) {
        Student s = repo.findById(id).orElse(null);
        if (s != null) {
            s.setName(student.getName());
            s.setAge(student.getAge());
            s.setEmail(student.getEmail());
            return repo.save(s);
        }
        return null;
    }

    public void delete(int id) {
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

    public com.example.schoolmanager.controller.Student create(com.example.schoolmanager.controller.Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
}