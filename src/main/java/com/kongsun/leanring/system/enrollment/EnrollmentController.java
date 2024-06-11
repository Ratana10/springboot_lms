package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid EnrollmentDTO dto) {

        enrollmentService.create(dto);

        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("create enrollment successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

}
