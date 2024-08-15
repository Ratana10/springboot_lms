package com.kongsun.leanring.system.enroll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Long>,
        JpaSpecificationExecutor<Enroll> {

    List<Enroll> findByStudentId(Long studentId);
}
