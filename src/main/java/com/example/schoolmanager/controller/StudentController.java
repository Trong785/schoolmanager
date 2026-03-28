package com.example.schoolmanager.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.schoolmanager.entity.Student;
import com.example.schoolmanager.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // ================== GET ALL ==================
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    // ================== GET BY ID ==================
    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return service.getById(id);
    }

    // ================== CREATE ==================
    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    // ================== UPDATE ==================
    @PutMapping("/{id}")
    public Student update(@PathVariable int id,
                          @RequestBody Student student) {
        return service.update(id, student);
    }

    // ================== DELETE ==================
    @DeleteMapping("/{id}")
    public HashMap<String, Object> delete(@PathVariable int id) {
        service.delete(id);

        HashMap<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("message", "Xóa thành công");

        return res;
    }

    // ================== SEARCH + PAGINATION ==================
    @GetMapping("/search")
    public Page<Student> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return service.search(keyword, page, size);
    }

    // ================== SEARCH BY NAME ==================
    @GetMapping("/search-by-name")
    public Page<Student> searchByName(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return service.searchByName(name, page, size);
    }

    // ================== UPDATE VIA POST ==================
    @PostMapping("/update/{id}")
    public Student updateViaPost(@PathVariable int id,
                                @RequestBody Student student) {
        return service.update(id, student);
    }

    // ================== DELETE VIA POST ==================
    @PostMapping("/delete/{id}")
    public HashMap<String, Object> deleteViaPost(@PathVariable int id) {
        service.delete(id);

        HashMap<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("message", "Xóa thành công");

        return res;
    }
}