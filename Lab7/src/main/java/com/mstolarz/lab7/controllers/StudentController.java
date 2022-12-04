package com.mstolarz.lab7.controllers;


import com.mstolarz.lab7.entities.Student;
import com.mstolarz.lab7.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAll() {
        return studentService.getStudentList();
    }

    @PostMapping("/add")
    public String add(@RequestBody Student student) {
        var newStudent = studentService.addStudent(student);
        return "Successfully added Student " + newStudent.getId() + " to Database";
    }

    @PutMapping("/edit")
    public String edit(@RequestBody Student student) {
        var editedStudent = studentService.editStudent(student);
        return "Successfully edited Student " + editedStudent.getId() + " in Database";
    }

    @DeleteMapping("/delete")
    public String delete(Long id) {
        studentService.deleteStudent(id);
        return "Successfully deleted Student " + id + " in Database";
    }
}
