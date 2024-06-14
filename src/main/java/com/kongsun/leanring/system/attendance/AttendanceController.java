package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid AttendanceRequest request) {
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(attendanceService.create(request))
                        .message("create attendance successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

}
