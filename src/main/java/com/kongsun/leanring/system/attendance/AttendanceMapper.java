package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.course.CourseService;
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

    @Mapping(target = "courseId", source = "course.id")
    AttendanceResponse toAttendanceResponse(Attendance attendance);
}
