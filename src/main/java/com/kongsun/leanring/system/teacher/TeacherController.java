package com.kongsun.leanring.system.teacher;

import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<Teacher> teachers = teacherService.getAll();

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(teachers.stream()
                                .map(teacherMapper::toTeacherDTO)
                                .toList())
                        .message("get teachers successfully")
                        .httpStatus(OK.value())
                        .build()
                );
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid TeacherDTO dto) {
        Teacher teacher = teacherMapper.toTeacher(dto);
        teacher = teacherService.create(teacher);

        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(teacherMapper.toTeacherDTO(teacher))
                        .message("create teacher successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);

        return ResponseEntity
                .status(FOUND)
                .body(ApiResponse.builder()
                        .data(teacherMapper.toTeacherDTO(teacher))
                        .message("get teacher successfully")
                        .httpStatus(FOUND.value())
                        .build()
                );
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid TeacherDTO dto) {
        Teacher teacher = teacherMapper.toTeacher(dto);
        teacher = teacherService.update(id, teacher);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(teacherMapper.toTeacherDTO(teacher))
                        .message("update teacher successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete teacher successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

}
