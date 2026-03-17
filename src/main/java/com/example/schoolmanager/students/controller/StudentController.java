package com.example.schoolmanager.students.controller;

import com.example.schoolmanager.students.dto.StudentDto;
import com.example.schoolmanager.students.dto.StudentListDto;
import com.example.schoolmanager.students.entity.Student;
import com.example.schoolmanager.students.mapper.StudentMapper;
import com.example.schoolmanager.students.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/admin/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @ModelAttribute("currentMenu")
    public String currentMenu() {
        return "students";
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<StudentListDto> studentPage =
                studentService.search(keyword, pageable);

        model.addAttribute("studentPage", studentPage);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        return "admin/students/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new StudentDto());
        model.addAttribute("isEdit", false);
        return "admin/students/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable UUID id,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        return studentService.findByIdAsDto(id)
                .map(dto -> {
                    model.addAttribute("student", dto);
                    model.addAttribute("isEdit", true);
                    return "admin/students/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên.");
                    return "redirect:/admin/students";
                });
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") StudentDto dto,
                       BindingResult result,
                       @RequestParam(defaultValue = "false") boolean isEdit,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", isEdit);
            return "admin/students/form";
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            if (!isEdit && studentService.findByEmail(dto.getEmail().trim()).isPresent()) {
                result.rejectValue("email", "duplicate", "Email đã tồn tại.");
            } else if (isEdit) {
                studentService.findByIdAsDto(dto.getId()).ifPresent(existing -> {
                    if (existing.getEmail() != null &&
                            !existing.getEmail().equalsIgnoreCase(dto.getEmail())
                            && studentService.findByEmail(dto.getEmail().trim()).isPresent()) {
                        result.rejectValue("email", "duplicate", "Email đã tồn tại.");
                    }
                });
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("isEdit", isEdit);
            return "admin/students/form";
        }

        Student entity = studentMapper.toEntity(dto);
        studentService.save(entity);
        redirectAttributes.addFlashAttribute("success",
                isEdit ? "Cập nhật sinh viên thành công." : "Thêm mới sinh viên thành công.");
        return "redirect:/admin/students";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        studentService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Đã xóa sinh viên.");
        return "redirect:/admin/students";
    }
}