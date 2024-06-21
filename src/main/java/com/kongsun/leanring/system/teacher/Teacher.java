package com.kongsun.leanring.system.teacher;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tea_id")
    private Long id;

    @Column(
            name = "tea_firstname",
            nullable = false,
            length = 50
    )
    private String firstname;

    @Column(
            name = "tea_lastname",
            nullable = false,
            length = 50
    )
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "gender",
            nullable = false
    )
    private Gender gender = Gender.MALE;

    @Column(
            name = "salary",
            nullable = false
    )
    private BigDecimal salary = BigDecimal.ZERO;

    @Column(
            name = "hire_date"
    )
    private LocalDate hireDate;

    @Column(
            name = "stop_work"
    )
    private boolean stopWork = false;

}
