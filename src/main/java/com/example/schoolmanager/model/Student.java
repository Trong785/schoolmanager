// package com.example.schoolmanager.model;


// import java.util.UUID;

// import org.hibernate.annotations.UuidGenerator;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "students")
// public class Student {
//     @Id
//     @GeneratedValue
//     @UuidGenerator   // Hibernate 6+
//     @Column(columnDefinition = "UNIQUEIDENTIFIER",
//             updatable = false,
//             nullable = false)
//     private UUID id;

//     //  NVARCHAR trên SQL Server
//     @Column(columnDefinition = "NVARCHAR(100)")
//     private String name;

//     @Column(columnDefinition = "NVARCHAR(100)")
//     private String email;

//     @Column(columnDefinition = "NVARCHAR(50)")
//     private String gender; // Nam, Nữ, Khác

//     // ===== Constructor =====
//     public Student() {}

//     public Student(String name, String email, String gender) {
//         this.name = name;
//         this.email = email;
//         this.gender = gender;
//     }

//     // ===== Getter Setter =====
//     public UUID getId() { return id; }
//     public void setId(UUID id) { this.id = id; }

//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getGender() { return gender; }
//     public void setGender(String gender) { this.gender = gender; }
// }
