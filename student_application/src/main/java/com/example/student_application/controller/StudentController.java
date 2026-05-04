package com.example.student_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.student_application.model.Student;
import com.example.student_application.service.StudentService;
import com.example.student_application.service.UserService;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private UserService userService;

    
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        boolean success = userService.login(username, password);
        if (success) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
    }

    
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    
    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         Model model) {
        userService.signup(username, password);
        model.addAttribute("success", "Account created! Please login.");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "dashboard";
    }

    @GetMapping("/add")
    public String addStudentPage() {
        return "add-student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        service.saveStudent(student);
        return "redirect:/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "edit-student";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        service.saveStudent(student);
        return "redirect:/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "redirect:/dashboard";
    }
}