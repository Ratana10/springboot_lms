package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.course.CourseService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface AttendanceDetailMapper {
    AttendanceDetailResponse toAttendanceDetailResponse(AttendanceDetail attendanceDetail);
}
