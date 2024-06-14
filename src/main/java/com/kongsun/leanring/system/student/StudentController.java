package com.kongsun.leanring.system.student;

import com.kongsun.leanring.system.enrollment.EnrollmentService;
import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<Student> students = studentService.getAll();

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(students.stream()
                                .map(studentMapper::toStudentDTO)
                                .toList())
                        .message("get students successfully")
                        .httpStatus(OK.value())
                        .build()
                );
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid StudentDTO dto) {
        Student student = studentMapper.toStudent(dto);
        student = studentService.create(student);

        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(studentMapper.toStudentDTO(student))
                        .message("create student successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);

        return ResponseEntity
                .status(FOUND)
                .body(ApiResponse.builder()
                        .data(studentMapper.toStudentDTO(student))
                        .message("get student successfully")
                        .httpStatus(FOUND.value())
                        .build()
                );
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid StudentDTO dto) {
        Student student = studentMapper.toStudent(dto);
        student = studentService.update(id, student);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(studentMapper.toStudentDTO(student))
                        .message("update student successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete student successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping("{studentId}/courses")
    public ResponseEntity<ApiResponse> getStudentEnrollmentCourses(@PathVariable Long studentId) {
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(enrollmentService.getStudentEnrollmentCourses(studentId))
                        .message("get student enrollment courses successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }
}
