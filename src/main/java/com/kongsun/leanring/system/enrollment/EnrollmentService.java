package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.course.CourseResponse;

import java.util.List;


public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentRequest enrollmentRequest);

    Enrollment getById(Long id);

    void deleteById(Long id);

    List<EnrollmentResponse> getAll();

    List<CourseResponse> getStudentEnrollmentCourses(Long studentId);

}
