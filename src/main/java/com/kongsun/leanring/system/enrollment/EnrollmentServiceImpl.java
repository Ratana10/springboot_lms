package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.course.Course;
import com.kongsun.leanring.system.course.CourseMapper;
import com.kongsun.leanring.system.course.CourseResponse;
import com.kongsun.leanring.system.exception.ApiException;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import com.kongsun.leanring.system.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kongsun.leanring.system.enrollment.EnrollmentStatus.UNPAID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "enrollmentsCache")
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentMapper enrollmentMapper;
    private final CourseMapper courseMapper;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;

    @Override
    @CacheEvict(allEntries = true)
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
    @Cacheable(key = "#id")
    public Enrollment getById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", id));

    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteById(Long id) {
        getById(id);
        enrollmentRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public PageDTO getAll(Map<String ,String > params) {
        Specification<Enrollment> spec = Specification.where(null);

        if(params.containsKey("search")){
            spec = EnrollmentSpec.containStudentName(params.get("search"));
        }

        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        return new PageDTO(enrollmentRepository.findAll(spec, pageable));
    }

    @Override
    public List<CourseResponse> getStudentEnrollmentCourses(Long studentId) {
        studentService.getById(studentId);
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return enrollments.stream()
                .flatMap(enr -> enr.getCourses().stream())
                .distinct()
                .map(courseMapper::toCourseResponse)
                .toList();

    }

    @Override
    public EnrollmentResponse update(Long id, EnrollmentRequest enrollmentRequest) {
        Enrollment byId = getById(id);
        return null;
    }

    private void checkStudentEnrollmentCourses(Long studentId, Set<Long> courseIds) {
        List<Enrollment> existingEnrollments = enrollmentRepository.findByStudentIdAndCourseIds(studentId, courseIds);

        if (!existingEnrollments.isEmpty()) {
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
    }
}
