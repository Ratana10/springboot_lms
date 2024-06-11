package com.kongsun.leanring.system.course;

import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;


    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<Course> courses = courseService.getAll();

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(courses.stream()
                                .map(courseMapper::toCourseDTO)
                                .toList())
                        .message("get courses successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid CourseDTO dto) {
        Course course = courseMapper.toCourse(dto);
        course = courseService.create(course);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(courseMapper.toCourseDTO(course))
                        .message("create course successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Course course = courseService.getById(id);
        return ResponseEntity
                .status(FOUND)
                .body(ApiResponse.builder()
                        .data(courseMapper.toCourseDTO(course))
                        .message("get course successfully")
                        .httpStatus(FOUND.value())
                        .build()
                );

    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid CourseDTO dto) {
        Course course = courseMapper.toCourse(dto);
        course = courseService.update(id, course);
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(courseMapper.toCourseDTO(course))
                        .message("update course successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity
                .status(FOUND)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete course successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PutMapping("{courseId}/teachers/{teacherId}")
    public ResponseEntity<ApiResponse> addTeacherToCourse(@PathVariable Long courseId, @PathVariable Long teacherId) {
        Course course = courseService.addTeacherToCourse(courseId, teacherId);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(courseMapper.toCourseDTO(course))
                        .message("add teacher to course successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PutMapping("{courseId}/teachers")
    public ResponseEntity<ApiResponse> removeTeacherFromCourse(@PathVariable Long courseId) {
        courseService.removeTeacherFromCourse(courseId);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(null)
                        .message("remove teacher from course successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

}
