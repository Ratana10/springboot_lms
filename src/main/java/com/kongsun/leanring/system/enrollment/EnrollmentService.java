package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.course.CourseResponse;

import java.util.List;
import java.util.Map;


public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentRequest enrollmentRequest);

    Enrollment getById(Long id);

    void deleteById(Long id);

    PageDTO getAll(Map<String ,String >params);

    List<CourseResponse> getStudentEnrollmentCourses(Long studentId);
    EnrollmentResponse update(Long id, EnrollmentRequest enrollmentRequest);
}
