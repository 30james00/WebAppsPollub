package com.mstolarz.pai_dto.services;

import com.mstolarz.pai_dto.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
}
