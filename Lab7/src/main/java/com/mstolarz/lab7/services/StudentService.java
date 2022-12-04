package com.mstolarz.lab7.services;

import com.mstolarz.lab7.entities.Student;
import com.mstolarz.lab7.entities.StudentRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudentList() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student editStudent(Student student) {
        if (!studentRepository.existsById(student.getId())) throw new ObjectNotFoundException(student, "Student");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) throw new ObjectNotFoundException(id, "Student");
        studentRepository.deleteById(id);
    }
}
