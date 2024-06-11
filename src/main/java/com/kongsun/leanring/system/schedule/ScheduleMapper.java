package com.kongsun.leanring.system.schedule;

import com.kongsun.leanring.system.course.CourseService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                CourseService.class
        }
)
public interface ScheduleMapper {
    @Mapping(target = "course", source = "courseId")
    Schedule toSchedule(ScheduleDTO dto);
    @Mapping(target = "courseId", source = "course.id")
    ScheduleDTO toScheduleDTO(Schedule schedule);
}
