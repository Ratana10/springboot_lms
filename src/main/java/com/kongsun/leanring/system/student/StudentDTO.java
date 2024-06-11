package com.kongsun.leanring.system.student;

import com.kongsun.leanring.system.teacher.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    @NotNull(message = "firstname is required")
    private String firstName;

    @NotNull(message = "lastname is required")
    private String lastName;

    private Gender gender;

    private String phone;

}
