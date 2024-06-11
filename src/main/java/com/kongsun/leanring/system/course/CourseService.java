package com.kongsun.leanring.system.course;

import java.util.List;


public interface CourseService {
    Course create(Course course);

    Course getById(Long id);

    Course update(Long id, Course course);

    void deleteById(Long id);

    List<Course> getAll();

}
