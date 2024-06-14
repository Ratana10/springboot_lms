package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.attendance_detail.AttendanceDetail;
import com.kongsun.leanring.system.attendance_detail.AttendanceDetailRepository;
import com.kongsun.leanring.system.student.Student;
import com.kongsun.leanring.system.student.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final AttendanceDetailRepository attendanceDetailRepository;
    private final StudentService studentService;
    private final AttendanceMapper attendanceMapper;

    @Override
    public Attendance create(AttendanceRequest attendanceRequest) {
        Attendance attendance = attendanceMapper.toAttendance(attendanceRequest);
        attendanceRepository.save(attendance);

        Map<AttendanceStatus, List<Long>> map = attendanceRequest.getAttendance();
        for (Map.Entry<AttendanceStatus, List<Long>> entry : map.entrySet()) {
            AttendanceStatus status = entry.getKey();
            List<Long> studentIds = entry.getValue();

            List<Student> students = studentService.getByIds(studentIds);
            List<AttendanceDetail> attendanceDetails = students.stream()
                    .map(stu ->
                            AttendanceDetail.builder()
                                    .attendance(attendance)
                                    .attendanceStatus(status)
                                    .student(stu)
                                    .build()

                    ).toList();
            attendanceDetailRepository.saveAll(attendanceDetails);

        }
        return attendance;
    }
}
