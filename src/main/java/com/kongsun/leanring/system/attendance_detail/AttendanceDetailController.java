package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.common.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/attendance-details")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AttendanceDetailController {
    private final AttendanceDetailService attendanceDetailService;

    @GetMapping
    public ResponseEntity<PageDTO> getAttendanceDetailByCourse(@RequestParam Map<String, String> params) {

        return ResponseEntity
                .ok(attendanceDetailService.getAttendanceDetailsByCourse(params));

    }
}
