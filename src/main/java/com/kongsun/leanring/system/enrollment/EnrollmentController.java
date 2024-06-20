package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid EnrollmentRequest request) {

        EnrollmentResponse enrollmentResponse = enrollmentService.create(request);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(enrollmentResponse)
                        .message("create enrollment successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {

        enrollmentService.deleteById(id);

        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete enrollment successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<EnrollmentResponse> enrollmentResponses = enrollmentService.getAll();
        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(enrollmentResponses)
                        .message("get all enrollment successfully")
                        .httpStatus(OK.value())
                        .build()
                );
    }

}
