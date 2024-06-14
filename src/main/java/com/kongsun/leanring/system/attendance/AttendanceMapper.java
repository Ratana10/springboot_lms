package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.category.Category;
import com.kongsun.leanring.system.category.CategoryDTO;
import com.kongsun.leanring.system.course.CourseService;
import com.kongsun.leanring.system.student.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                CourseService.class
        }
)
public interface AttendanceMapper {
    @Mapping(target = "course", source = "courseId")
    Attendance toAttendance(AttendanceRequest request);
}
