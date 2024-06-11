package com.kongsun.leanring.system.enrollment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentMapper enrollmentMapper;
    @Override
    public EnrollmentDTO create(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = enrollmentMapper.toEnrollment(enrollmentDTO);
        return null;
    }

    @Override
    public Enrollment getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Enrollment> getAll() {
        return null;
    }
}
