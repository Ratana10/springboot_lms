package com.kongsun.leanring.system.enroll;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.Map;


public interface EnrollService {
    EnrollResponse create(EnrollRequest enrollmentRequest);

    Enroll getById(Long id);

    void deleteById(Long id);

    PageDTO getAll(Map<String, String> params);

}
