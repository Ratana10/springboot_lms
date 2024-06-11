package com.kongsun.leanring.system.teacher;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TeacherDTO {

    @NotNull(message = "firstname is required")
    private String firstName;

    @NotNull(message = "lastname is required")
    private String lastName;


    private Gender gender = Gender.MALE;


    private BigDecimal salary = BigDecimal.ZERO;


    private LocalDate hireDate;

    private boolean stopWork;

}
