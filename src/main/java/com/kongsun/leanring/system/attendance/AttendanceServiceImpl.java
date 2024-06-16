package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.attendance_detail.AttendanceDetail;
import com.kongsun.leanring.system.attendance_detail.AttendanceDetailRepository;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import com.kongsun.leanring.system.student.Student;
import com.kongsun.leanring.system.student.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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
        return getAttendanceResponse(attendances, attendanceDetails);
    }

    @Override
    public List<?> getAllAttendanceByCourseId(Long courseId) {
        List<Attendance> attCourse = attendanceRepository.findByCourseId(courseId);
        List<Long> attIds = attCourse.stream().map(Attendance::getId).toList();

        List<AttendanceDetail> attDetails = attendanceDetailRepository.findByAttendanceIdIn(attIds);

        //grouping attDetails by attId
        Map<Long, List<AttendanceDetail>> attendanceDetailsMap = attDetails.stream()
                .collect(Collectors.groupingBy(attendanceDetail -> attendanceDetail.getAttendance().getId()));

        //Map attRecord to attResponse
        List<AttendanceResponse> listAttResponse = attCourse.stream()
                .map(att -> {
                    List<AttendanceDetail> attDetail = attendanceDetailsMap.getOrDefault(att.getId(), Collections.emptyList());

                    return getAttendanceResponse(att, attDetail);
                })
                .toList();

        return listAttResponse;

    }

    private AttendanceResponse getAttendanceResponse(Attendance att, List<AttendanceDetail> attDetail) {
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
        return attResponse;
    }
}
