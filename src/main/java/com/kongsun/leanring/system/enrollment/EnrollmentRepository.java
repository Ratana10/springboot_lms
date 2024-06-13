package com.kongsun.leanring.system.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("""
        SELECT enr FROM Enrollment enr
        JOIN enr.courses cou
        WHERE enr.student.id = :studentId AND cou.id = :courseId
    """)
    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
