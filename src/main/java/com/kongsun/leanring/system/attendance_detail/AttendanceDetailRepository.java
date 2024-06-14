package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceDetailRepository extends JpaRepository<AttendanceDetail, Long> {
}
