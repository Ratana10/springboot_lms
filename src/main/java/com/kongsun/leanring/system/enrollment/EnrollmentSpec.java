package com.kongsun.leanring.system.enrollment;

import org.springframework.data.jpa.domain.Specification;

public class EnrollmentSpec {
    public static Specification<Enrollment> containStudentName(String studentName) {
        return (root, query, criteriaBuilder) -> {
            var firstname = criteriaBuilder.like(
                    criteriaBuilder.lower(criteriaBuilder.lower(root.join("student").get("firstname"))), "%" + studentName.toLowerCase() + "%");

            var lastname = criteriaBuilder.like(
                    criteriaBuilder.lower(criteriaBuilder.lower(root.join("student").get("lastname"))), "%" + studentName.toLowerCase() + "%");

            // combine the predicate or
            return criteriaBuilder.or(firstname, lastname);
        };
    }

    public static Specification<Enrollment> equalStatus(String status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);

    }
}
