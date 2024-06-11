package com.kongsun.leanring.system.course;

import com.kongsun.leanring.system.category.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoryService.class
        }
)
public interface CourseMapper {
    @Mapping(
            target = "category", source = "categoryId"
    )
    Course toCourse(CourseDTO dto);

    @Mapping(
            target = "categoryId", source = "category.id"
    )
    CourseDTO toCourseDTO(Course course);
}
