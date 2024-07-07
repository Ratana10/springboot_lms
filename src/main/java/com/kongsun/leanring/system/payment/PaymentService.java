package com.kongsun.leanring.system.payment;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.Map;

public interface PaymentService {
    Payment create(Payment payment);

    Payment getById(Long id);

    void deleteById(Long id);

    PageDTO getAll(Map<String, String> params);

}
