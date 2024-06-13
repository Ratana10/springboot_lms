package com.kongsun.leanring.system.enrollment;

import java.util.List;


public interface EnrollmentService {
    EnrollmentRequest create(EnrollmentRequest enrollmentRequest);
    Enrollment getById(Long id);

    void deleteById(Long id);

    List<Enrollment> getAll();

}
