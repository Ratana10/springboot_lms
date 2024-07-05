package com.kongsun.leanring.system.course;

import com.kongsun.leanring.system.category.CategoryService;
import com.kongsun.leanring.system.teacher.TeacherService;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoryService.class,
                TeacherService.class
        }
)

public interface CourseMapper {
    @Mappings({
            @Mapping(target = "category", source = "categoryId"),
            @Mapping(target = "teacher", source = "teacherId")
    })
    Course toCourse(CourseRequest request);
    @Mappings({
            @Mapping(target = "categoryId", source = "category.id"),
            @Mapping(target = "teacherId", source = "teacher.id")
    })
    CourseResponse toCourseResponse(Course course);

}
