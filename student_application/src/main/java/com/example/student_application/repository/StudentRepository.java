package com.example.student_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student_application.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}