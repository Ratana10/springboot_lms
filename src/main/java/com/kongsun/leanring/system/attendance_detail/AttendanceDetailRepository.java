package com.kongsun.leanring.system.attendance_detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceDetailRepository extends JpaRepository<AttendanceDetail, Long> {
    List<AttendanceDetail> findByAttendanceId(Long attendanceId);
}
