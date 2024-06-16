package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.attendance_detail.AttendanceDetail;
import com.kongsun.leanring.system.attendance_detail.AttendanceDetailRepository;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import com.kongsun.leanring.system.student.Student;
import com.kongsun.leanring.system.student.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<?> getAll() {
        List<Attendance> attendances = attendanceRepository.findAll();


        return attendances.stream().map(attendanceMapper::toAttendanceResponse).toList();
    }

    @Override
    public AttendanceResponse getById(Long id) {
        Attendance attendances = attendanceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Attendance", id));

        List<AttendanceDetail> attendanceDetails = attendanceDetailRepository.findByAttendanceId(id);
        Map<AttendanceStatus, List<Long>> attendanceMap = attendanceDetails.stream()
                .collect(
                        Collectors.groupingBy(
                        AttendanceDetail::getAttendanceStatus,
                        Collectors.mapping(attDetail -> attDetail.getStudent().getId(),
                        Collectors.toList())
                ));

        AttendanceResponse attendanceResponse = attendanceMapper.toAttendanceResponse(attendances);
        attendanceResponse.setAttendance(attendanceMap);

        return attendanceResponse;
    }

    @Override
    public List<?> getAllAttendanceByCourseId(Long courseId) {
        List<Attendance> attendanceByCourse = attendanceRepository.findByCourseId(courseId);

        List<AttendanceResponse> listAttResponse = new ArrayList<>();
        for (Attendance att: attendanceByCourse){
            List<AttendanceDetail> attDetail = attendanceDetailRepository.findByAttendanceId(att.getId());

            Map<AttendanceStatus, List<Long>> attendanceMap = attDetail.stream()
                    .collect(
                            Collectors.groupingBy(
                                    AttendanceDetail::getAttendanceStatus,
                                    Collectors.mapping(
                                            ad -> ad.getStudent().getId(), Collectors.toList()
                                    )
                            )
                    );

            AttendanceResponse attResponse = attendanceMapper.toAttendanceResponse(att);
            attResponse.setAttendance(attendanceMap);
            listAttResponse.add(attResponse);
        }

        return listAttResponse;
    }
}
