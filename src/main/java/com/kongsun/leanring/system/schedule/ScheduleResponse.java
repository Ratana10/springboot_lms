package com.kongsun.leanring.system.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ScheduleResponse {
    private Long id;

    private Day day;

    private LocalTime startTime;

    private LocalTime endTime;

    private Long courseId;
}
