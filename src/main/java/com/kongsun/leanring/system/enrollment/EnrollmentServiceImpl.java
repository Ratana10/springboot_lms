package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.course.Course;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.kongsun.leanring.system.enrollment.EnrollmentStatus.*;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentMapper enrollmentMapper;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public EnrollmentResponse create(EnrollmentRequest enrollmentRequest) {
        Enrollment enrollment = enrollmentMapper.toEnrollment(enrollmentRequest);

        BigDecimal total = enrollment.getCourses().stream()
                .map(Course::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        enrollment.setTotal(total);
        enrollment.setRemain(total);
        enrollment.setStatus(UNPAID);

        enrollmentRepository.save(enrollment);

        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public Enrollment getById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Enrollment", id));
    }

    @Override
    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public List<EnrollmentResponse> getAll() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        return enrollments.stream()
                .map(enrollmentMapper::toEnrollmentResponse)
                .toList();
    }

    private boolean isStudentEnrollmentInCourse(Long studentId, Long courseId) {
        return true;
    }
}
