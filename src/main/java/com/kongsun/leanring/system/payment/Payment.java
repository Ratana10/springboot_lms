package com.kongsun.leanring.system.payment;

import com.kongsun.leanring.system.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enr_id")
    private Enrollment enrollment;

    @Column(
            name = "pay_amount",
            nullable = false
    )
    private BigDecimal amount;

    @Column(
            name = "pay_date",
            nullable = false
    )
    private LocalDate date;
}
