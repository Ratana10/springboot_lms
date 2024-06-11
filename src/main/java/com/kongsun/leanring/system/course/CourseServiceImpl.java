package com.kongsun.leanring.system.course;

import com.kongsun.leanring.system.exception.ApiException;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import com.kongsun.leanring.system.teacher.Teacher;
import com.kongsun.leanring.system.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<Course> getCoursesByTeacherId(Long teacherId) {
        teacherService.getById(teacherId);
        List<Course> courses = courseRepository.findByTeacherId(teacherId);
        if(courses.isEmpty()){
            throw new ApiException(
                    HttpStatus.NOT_FOUND,
                    String.format("teacher id=%d don't have the course to teach", teacherId));
        }
        return courses;
    }

    @Override
    public Set<Course> findCoursesByIds(Set<Long> courseIds) {
        return new HashSet<>(courseRepository.findAllById(courseIds));
    }
}
