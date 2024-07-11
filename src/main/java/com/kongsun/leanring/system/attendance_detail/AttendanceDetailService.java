package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.Map;

public interface AttendanceDetailService {
    PageDTO getAttendanceDetailsByCourse(Map<String, String> params);
}
