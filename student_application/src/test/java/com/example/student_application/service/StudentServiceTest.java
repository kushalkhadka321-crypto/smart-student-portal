package com.example.student_application.service;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.student_application.model.Student;
import com.example.student_application.repository.StudentRepository;

public class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    public StudentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveStudent() {
        Student student = new Student();
        student.setName("Test");

        service.saveStudent(student);

        verify(repository, times(1)).save(student);
    }

    @Test
    public void testGetAllStudents() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));

        assertEquals(2, service.getAllStudents().size());
    }
}
