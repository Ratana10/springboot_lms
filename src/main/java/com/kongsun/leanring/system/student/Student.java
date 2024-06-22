package com.kongsun.leanring.system.student;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.teacher.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id")
    private Long id;

    @Column(
            name = "stu_firstname",
            nullable = false,
            length = 50
    )
    private String firstname;

    @Column(
            name = "stu_lastname",
            nullable = false,
            length = 50
    )
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "stu_gender",
            nullable = false
    )
    private Gender gender = Gender.MALE;

    @Column(
            name = "stu_phone",
            length = 12,
            unique = true
    )
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;

}
