package com.mstolarz.pai_dto.services;

import com.mstolarz.pai_dto.converters.StudentConverter;
import com.mstolarz.pai_dto.dtos.StudentDto;
import com.mstolarz.pai_dto.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentConverter::convert)
                .collect(Collectors.toList());
    }
}
