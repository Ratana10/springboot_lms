package com.kongsun.leanring.system.payment;

import com.kongsun.leanring.system.enrollment.EnrollmentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                EnrollmentService.class
        }
)
public interface PaymentMapper {
    @Mapping(
            target = "enrollment", source = "enrollmentId"
    )
    Payment toPayment(PaymentDTO dto);

   @Mapping(target = "enrollmentId", source = "enrollment.id")
    PaymentResponse toPaymentResponse(Payment payment);
}
