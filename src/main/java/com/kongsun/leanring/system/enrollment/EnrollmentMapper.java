package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.course.CourseService;
import com.kongsun.leanring.system.student.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                CourseService.class,
                EnrollmentService.class,
                StudentService.class
        }
)
public interface EnrollmentMapper {
    @Mappings({
            @Mapping(target = "student", source = "studentId"),
            @Mapping(target = "courses", source = "courseIds"),
    })
    Enrollment toEnrollment(EnrollmentRequest dto);
}
