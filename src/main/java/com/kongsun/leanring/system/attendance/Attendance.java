package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.course.Course;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "attendances")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cou_id")
    private Course course;

    @Column(name = "att_date")
    private LocalDate date;
}
