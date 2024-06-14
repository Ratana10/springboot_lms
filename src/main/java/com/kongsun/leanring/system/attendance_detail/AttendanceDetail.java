package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.attendance.Attendance;
import com.kongsun.leanring.system.attendance.AttendanceStatus;
import com.kongsun.leanring.system.student.Student;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance_details")
public class AttendanceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "att_id")
    private Attendance attendance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stu_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AttendanceStatus attendanceStatus;
}
