package com.kongsun.leanring.system.course;

import com.kongsun.leanring.system.category.CategoryService;
import com.kongsun.leanring.system.teacher.TeacherService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoryService.class,
                TeacherService.class
        }
)
public interface CourseMapper {
//    @Mapping(
//            target = "category", source = "categoryId"
//    )
//    Course toCourse(CourseDTO dto);
//
//    @Mappings({
//            @Mapping(target = "categoryId", source = "category.id"),
//            @Mapping(target = "teacherId", source = "teacher.id")
//    })
//    CourseDTO toCourseDTO(Course course);
    @Mapping(
            target = "category", source = "categoryId"
    )
    Course toCourse(CourseRequest request);
    @Mappings({
            @Mapping(target = "categoryId", source = "category.id"),
            @Mapping(target = "teacherId", source = "teacher.id")
    })
    CourseResponse toCourseResponse(Course course);
}
