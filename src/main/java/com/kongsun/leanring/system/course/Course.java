package com.kongsun.leanring.system.course;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.category.Category;
import com.kongsun.leanring.system.teacher.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cou_id")
    private Long id;

    @Column(
            name = "cou_name",
            nullable = false,
            length = 50
    )
    private String name;

    @Column(
            name = "cou_description"
    )
    private String description;

    @Column(
            name = "cou_price",
            nullable = false
    )
    private BigDecimal price = BigDecimal.ZERO;

    @Column(
            name = "cou_image"
    )
    private String image;

    @ManyToOne
    @JoinColumn(
            name = "cat_id",
            nullable = false
    )
    private Category category;

    @ManyToOne
    @JoinColumn(name = "tea_id")
    private Teacher teacher;

}
