package com.kongsun.leanring.system.teacher;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface TeacherMapper {
    Teacher toTeacher(TeacherRequest dto);

    TeacherResponse toTeacherResponse(Teacher teacher);
}
