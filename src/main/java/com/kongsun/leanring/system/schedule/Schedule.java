package com.kongsun.leanring.system.schedule;

import com.kongsun.leanring.system.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sch_id")
    private Long id;

    @Column(
            name = "day",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Day day;

    @Column(
            name = "start_time",
            nullable = false
    )
    private LocalTime startTime;

    @Column(
            name = "end_time",
            nullable = false
    )
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(
            name = "cou_id",
            nullable = false
    )
    private Course course;
}
