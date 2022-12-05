package com.mstolarz.pai_dto.converters;

import com.mstolarz.pai_dto.domain.Student;
import com.mstolarz.pai_dto.dtos.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentMapper {

    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "state", source = "address.state")
    @Mapping(target = "zip", source = "address.zip")
    StudentDto mapStudentToStudentDto(Student student);
}
