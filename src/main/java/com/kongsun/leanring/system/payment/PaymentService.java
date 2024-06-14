package com.kongsun.leanring.system.payment;

public interface PaymentService {
    PaymentResponse create(PaymentDTO paymentDTO);

    Payment getById(Long id);

    void deleteById(Long id);

}
