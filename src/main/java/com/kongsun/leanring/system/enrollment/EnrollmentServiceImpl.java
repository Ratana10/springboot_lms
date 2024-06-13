package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.course.Course;
import com.kongsun.leanring.system.exception.ApiException;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kongsun.leanring.system.enrollment.EnrollmentStatus.*;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentMapper enrollmentMapper;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {
        checkStudentEnrollmentCourses(request.getStudentId(), request.getCourseIds());

        Enrollment enrollment = enrollmentMapper.toEnrollment(request);


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

    private boolean checkStudentEnrollmentCourses(Long studentId, Set<Long> courseIds) {
        List<Enrollment> existingEnrollments = enrollmentRepository.findByStudentIdAndCourseIds(studentId, courseIds);

        if(!existingEnrollments.isEmpty()){
            String enrolledCourseIds = existingEnrollments.stream()
                    .flatMap(enr -> enr.getCourses().stream())
                    .filter(course -> courseIds.contains(course.getId()))
                    .map(course -> course.getId().toString())
                    .collect(Collectors.joining(", "));

            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "Student ID " + studentId + " is already enrolled in courses with IDs: " + enrolledCourseIds
            );
        }


        return true;
    }
}
