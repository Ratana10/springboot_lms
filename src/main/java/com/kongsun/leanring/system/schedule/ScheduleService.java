package com.kongsun.leanring.system.schedule;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.List;
import java.util.Map;


public interface ScheduleService {
    Schedule create(Schedule schedule);

    Schedule getById(Long id);

    Schedule update(Long id, Schedule schedule);

    void deleteById(Long id);

    PageDTO getAll(Map<String,String> params);
    List<Schedule> getSchedulesByCourseId(Long courseId);

}
