package com.kongsun.leanring.system.enrollment;

import java.util.List;


public interface EnrollmentService {
    EnrollmentDTO create(EnrollmentDTO enrollmentDTO);
    Enrollment getById(Long id);

    void deleteById(Long id);

    List<Enrollment> getAll();

}
