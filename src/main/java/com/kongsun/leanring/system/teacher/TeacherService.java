package com.kongsun.leanring.system.teacher;

import java.util.List;


public interface TeacherService {
    Teacher create(Teacher teacher);

    Teacher getById(Long id);

    Teacher update(Long id, Teacher teacher);

    void deleteById(Long id);

    List<Teacher> getAll();

}
