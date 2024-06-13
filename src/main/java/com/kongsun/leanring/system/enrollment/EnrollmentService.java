package com.kongsun.leanring.system.enrollment;

import java.util.List;


public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentRequest enrollmentRequest);
    Enrollment getById(Long id);

    void deleteById(Long id);

    List<EnrollmentResponse> getAll();

}
