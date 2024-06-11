package com.kongsun.leanring.system.teacher;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface TeacherMapper {
    Teacher toTeacher(TeacherDTO dto);

    TeacherDTO toTeacherDTO(Teacher teacher);
}
