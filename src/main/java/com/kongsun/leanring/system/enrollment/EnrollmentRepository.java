package com.kongsun.leanring.system.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>,
        JpaSpecificationExecutor<Enrollment> {
    @Query("SELECT e FROM Enrollment e JOIN e.courses c WHERE e.student.id = :studentId AND c.id IN :courseIds")
    List<Enrollment> findByStudentIdAndCourseIds(Long studentId, Set<Long> courseIds);

    List<Enrollment> findByStudentId(Long studentId);
}
