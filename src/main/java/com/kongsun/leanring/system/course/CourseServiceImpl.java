package com.kongsun.leanring.system.course;

import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import com.kongsun.leanring.system.teacher.Teacher;
import com.kongsun.leanring.system.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
    }

    @Override
    public Course update(Long id, Course course) {
        Course byId = getById(id);

        byId.setId(course.getId());
        byId.setName(course.getName());
        byId.setDescription(course.getDescription());
        byId.setPrice(course.getPrice());
        byId.setImage(course.getImage());
        byId.setCategory(course.getCategory());
        return courseRepository.save(byId);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course addTeacherToCourse(Long courseId, Long teacherId) {
        Course course = getById(courseId);
        Teacher teacher = teacherService.getById(teacherId);
        course.setTeacher(teacher);
        return courseRepository.save(course);
    }

    @Override
    public void removeTeacherFromCourse(Long courseId) {
        Course course = getById(courseId);
        course.setTeacher(null);
        courseRepository.save(course);
    }
}
