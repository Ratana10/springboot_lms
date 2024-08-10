package com.kongsun.leanring.system.payment;

import com.kongsun.leanring.system.enrollment.Enrollment;
import com.kongsun.leanring.system.student.Student;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class PaymentSpec {
    public static Specification<Payment> containFirstname(String firstname) {
        return (root, query, criteriaBuilder) ->{
            Join<Payment, Enrollment> enrollmentJoin = root.join("enrollment", JoinType.INNER);
            Join<Enrollment, Student> studentJoin = enrollmentJoin.join("student", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("firstname")), "%" + firstname.toLowerCase() + "%");
        };
    }

    public static Specification<Payment> containLastname(String lastname) {
        return (root, query, criteriaBuilder) -> {
            Join<Payment, Enrollment> enrollmentJoin = root.join("enrollment", JoinType.INNER);
            Join<Enrollment, Student> studentJoin = enrollmentJoin.join("student", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(studentJoin.get("lastname")), "%" + lastname.toLowerCase() + "%");
        };
    }

}
