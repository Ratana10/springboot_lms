package com.kongsun.leanring.system.attendance;

import java.util.List;

public interface AttendanceService {
    Attendance create(AttendanceRequest attendanceRequest);

    List<?> getAll();

    AttendanceResponse getById(Long id);
    List<?> getAllAttendanceByCourseId(Long courseId);
}
