package com.kongsun.leanring.system.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentDTO {
    @NotNull(message = "enrollmentId is required")
    private Long enrollmentId;

    @PositiveOrZero(message = "amount must be positive or zero")
    @Min(value = 1L, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "date is required")
    private LocalDate date;
}
