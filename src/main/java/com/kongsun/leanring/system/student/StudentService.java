package com.kongsun.leanring.system.student;

import java.util.List;
import java.util.Map;


public interface StudentService {
    Student create(Student student);

    Student getById(Long id);

    Student update(Long id, Student student);

    void deleteById(Long id);

    List<Student> getAll();

    List<Student> getAll(Map<String , String> params);

    List<Student> getByIds(List<Long> studentIds);
}
