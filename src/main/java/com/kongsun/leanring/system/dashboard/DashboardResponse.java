package com.kongsun.leanring.system.dashboard;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private long totalCourses;
    private long totalStudents;
    private long totalTeachers;
}