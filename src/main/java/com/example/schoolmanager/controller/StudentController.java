package com.example.schoolmanager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin // cho phép frontend gọi
public class StudentController {
    
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Student getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    // CREATE
    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student update(@PathVariable UUID id,
                          @RequestBody Student student) {
        return service.update(id, student);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public java.util.HashMap<String, Object> delete(@PathVariable UUID id) {
        service.delete(id);
        java.util.HashMap<String, Object> response = new java.util.HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa thành công");
        return response;
    }

    // SEARCH + PHÂN TRANG (gần đúng theo tên/email)
    @GetMapping("/search")
    public Page<Student> search(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return service.search(keyword, page, size);
    }

    // SEARCH BY NAME (riêng biệt)
    @GetMapping("/search-by-name")
    public Page<Student> searchByName(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return service.searchByName(name, page, size);
    }

    // UPDATE VIA POST (Yêu cầu 6)
    @PostMapping("/update/{id}")
    public Student updateViaPost(@PathVariable UUID id,
                                 @RequestBody Student student) {
        return service.update(id, student);
    }

    // DELETE VIA POST (Yêu cầu 2)
    @PostMapping("/delete/{id}")
    public java.util.HashMap<String, Object> deleteViaPost(@PathVariable UUID id) {
        service.delete(id);
        java.util.HashMap<String, Object> response = new java.util.HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa thành công");
        return response;
    }

}
