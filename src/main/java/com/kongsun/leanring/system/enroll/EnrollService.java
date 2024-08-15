package com.kongsun.leanring.system.enroll;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.List;
import java.util.Map;


public interface EnrollService {
    EnrollResponse create(EnrollRequest enrollmentRequest);

    Enroll getById(Long id);
    List<Enroll> getByStudentId(Long studentId);

    void deleteById(Long id);

    PageDTO getAll(Map<String, String> params);

}
