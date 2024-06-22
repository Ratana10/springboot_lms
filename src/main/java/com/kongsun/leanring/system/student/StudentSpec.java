package com.kongsun.leanring.system.student;

import org.springframework.data.jpa.domain.Specification;

public class StudentSpec {
    public static Specification<Student> containFirstname(String firstname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstname.toLowerCase() + "%");
    }

    public static Specification<Student> containLastname(String lastname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastname.toLowerCase() + "%");
    }

    public static Specification<Student> containPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + phone + "%");
    }

    public static Specification<Student> hasPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("phone"), phone);
    }

    public static Specification<Student> hasPhoneExcludingId(String phone, Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("phone"), phone),
                        criteriaBuilder.notEqual(root.get("id"), id)
                );
    }

    public static Specification<Student> containGender(String gender) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.upper(root.get("gender")), gender.toUpperCase());
    }


}
