package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.attendance_detail.AttendanceDetail;
import com.kongsun.leanring.system.attendance_detail.AttendanceDetailRepository;
import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import com.kongsun.leanring.system.student.Student;
import com.kongsun.leanring.system.student.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public AttendanceResponse create(AttendanceRequest attendanceRequest) {
        Attendance attendance = attendanceMapper.toAttendance(attendanceRequest);
        attendanceRepository.save(attendance);

        Map<AttendanceStatus, List<Long>> map = attendanceRequest.getAttendance();
        List<AttendanceDetail> attendanceDetails = map.entrySet().stream()
                .flatMap(entry -> {
                    AttendanceStatus status = entry.getKey();
                    List<Long> studentIds = entry.getValue();
                    List<Student> students = studentService.getByIds(studentIds);
                    return students.stream().map(stu ->
                            AttendanceDetail.builder()
                                    .attendance(attendance)
                                    .attendanceStatus(status)
                                    .student(stu)
                                    .build()
                    );
                })
                .toList();

        attendanceDetailRepository.saveAll(attendanceDetails);
        return getAttendanceResponse(attendance, attendanceDetails);
    }

    @Override
    public PageDTO getAll(Map<String ,String> params) {
        Specification<Attendance> spec = Specification.where(null);
        if(params.containsKey("startDate") && params.containsKey("endDate")){
            LocalDate startDate = LocalDate.parse(params.get("startDate"));
            LocalDate endDate = LocalDate.parse(params.get("endDate"));
            spec = AttendanceSpec.betweenDate(startDate, endDate);
        }
        if(params.containsKey("courseId")){
            spec = AttendanceSpec.containCourseId(Long.parseLong(params.get("courseId")));
        }

        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        Page<Attendance> atts = attendanceRepository.findAll(spec, pageable);

        List<Long> attIds = atts.stream().map(Attendance::getId).toList();
        List<AttendanceDetail> attDetails = attendanceDetailRepository.findByAttendanceIdIn(attIds);

        //grouping attDetails by attId
        Map<Long, List<AttendanceDetail>> attendanceDetailsMap = attDetails.stream()
                .collect(Collectors.groupingBy(attendanceDetail -> attendanceDetail.getAttendance().getId()));

        //Map attRecord to attResponse
        List<AttendanceResponse> attResponse = atts.stream()
                .map(att -> {
                    List<AttendanceDetail> attDetail = attendanceDetailsMap.getOrDefault(att.getId(), Collections.emptyList());

                    return getAttendanceResponse(att, attDetail);
                })
                .toList();

        return new PageDTO(new PageImpl<>(attResponse, pageable, atts.getTotalElements()));
    }

    @Override
    public AttendanceResponse getById(Long id) {
        Attendance attendances = attendanceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Attendance", id));

        List<AttendanceDetail> attendanceDetails = attendanceDetailRepository.findByAttendanceId(id);
        return getAttendanceResponse(attendances, attendanceDetails);
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
