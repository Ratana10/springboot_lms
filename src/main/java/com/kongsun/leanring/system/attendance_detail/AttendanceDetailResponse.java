package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.attendance.AttendanceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AttendanceDetailResponse {
    private AttendanceStatus status;
    private List<Long> studentIds;

}
