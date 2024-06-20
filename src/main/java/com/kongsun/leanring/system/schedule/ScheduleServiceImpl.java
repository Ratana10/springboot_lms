package com.kongsun.leanring.system.schedule;

import com.kongsun.leanring.system.course.CourseService;
import com.kongsun.leanring.system.exception.ApiException;
import com.kongsun.leanring.system.exception.ErrorResponse;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "schedulesCache")
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CourseService courseService;

    @Override
    @CacheEvict(allEntries = true)
    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    @Cacheable(key = "#id")
    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", id));
    }

    @Override
    @CacheEvict(allEntries = true)
    public Schedule update(Long id, Schedule schedule) {
        Schedule byId = getById(id);
        byId.setDay(schedule.getDay());
        byId.setStartTime(schedule.getStartTime());
        byId.setEndTime(schedule.getEndTime());
        byId.setCourse(schedule.getCourse());
        return scheduleRepository.save(byId);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteById(Long id) {
        getById(id);
        scheduleRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    @Cacheable(key = "#courseId")
    public List<Schedule> getSchedulesByCourseId(Long courseId) {
        courseService.getById(courseId);

        List<Schedule> schedules = scheduleRepository.findByCourseId(courseId);
        if (schedules.isEmpty()){
            throw new ApiException(HttpStatus.NOT_FOUND, "don't have schedule");
        }
        return schedules;
    }
}
