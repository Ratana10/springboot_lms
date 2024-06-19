package com.kongsun.leanring.system.course;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.exception.ApiException;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import com.kongsun.leanring.system.teacher.Teacher;
import com.kongsun.leanring.system.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "coursesCache")
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;

    @Override
    @CachePut(key = "#result.id")
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Cacheable(key = "#id")
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
    }

    @Override
    @CachePut(key = "#id")
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
    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        getById(id);
        courseRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public PageDTO getAll(Map<String,String> params) {
        Specification<Course> spec = Specification.where(null);
        if(params.containsKey("name")){
            spec = spec.and(CourseSpec.containName(params.get("name")))
                    .or(CourseSpec.containDescription(params.get("name")))
                    .or(CourseSpec.containCategoryName(params.get("name")));
        }

        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        return new PageDTO(courseRepository.findAll(spec, pageable));
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
    @Cacheable(key = "#teacherId")
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
    @Cacheable(key = "#courseIds")
    public Set<Course> findCoursesByIds(Set<Long> courseIds) {
        return new HashSet<>(courseRepository.findAllById(courseIds));
    }
}
