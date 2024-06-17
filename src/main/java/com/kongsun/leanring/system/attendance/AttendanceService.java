package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.List;
import java.util.Map;

public interface AttendanceService {
    AttendanceResponse create(AttendanceRequest attendanceRequest);

    PageDTO getAll(Map<String ,String> params);

    AttendanceResponse getById(Long id);
}
