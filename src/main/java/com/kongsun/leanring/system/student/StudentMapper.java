package com.kongsun.leanring.system.student;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface StudentMapper {
    Student toStudent(StudentRequest request);

    StudentResponse toStudentResponse(Student student);
}
