package com.kongsun.leanring.system.schedule;

import java.util.List;


public interface ScheduleService {
    Schedule create(Schedule schedule);

    Schedule getById(Long id);

    Schedule update(Long id, Schedule schedule);

    void deleteById(Long id);

    List<Schedule> getAll();
    List<Schedule> getSchedulesByCourseId(Long courseId);

}
