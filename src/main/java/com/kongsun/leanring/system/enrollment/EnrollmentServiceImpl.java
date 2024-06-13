package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.course.Course;
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
    public EnrollmentRequest create(EnrollmentRequest enrollmentRequest) {
        Enrollment enrollment = enrollmentMapper.toEnrollment(enrollmentRequest);

        BigDecimal total = enrollment.getCourses().stream()
                .map(Course::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        enrollment.setTotal(total);
        enrollment.setRemain(total);
        enrollment.setStatus(UNPAID);

        enrollmentRepository.save(enrollment);

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

    private boolean isStudentEnrollmentInCourse(Long studentId, Long courseId) {
        return true;
    }
}
