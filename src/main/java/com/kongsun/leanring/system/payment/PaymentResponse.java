package com.kongsun.leanring.system.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentResponse {
    private Long enrollmentId;
    private BigDecimal amount;
    private BigDecimal change;
    private LocalDate date;
}
