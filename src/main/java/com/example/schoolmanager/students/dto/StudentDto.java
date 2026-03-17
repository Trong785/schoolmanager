package com.example.schoolmanager.students.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

    private UUID id;

    @NotBlank(message = "Tên sinh viên không được trống")
    @Size(max = 150, message = "Tên tối đa 150 ký tự")
    private String name;

    @Email(message = "Email không hợp lệ")
    @Size(max = 150, message = "Email tối đa 150 ký tự")
    private String email;
}