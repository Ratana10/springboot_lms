package com.kongsun.leanring.system.student;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface StudentMapper {
    Student toStudent(StudentDTO dto);

    StudentDTO toStudentDTO(Student student);
}
