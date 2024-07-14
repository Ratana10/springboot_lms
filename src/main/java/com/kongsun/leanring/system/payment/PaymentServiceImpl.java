package com.kongsun.leanring.system.payment;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.enrollment.Enrollment;
import com.kongsun.leanring.system.enrollment.EnrollmentRepository;
import com.kongsun.leanring.system.exception.ApiException;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.kongsun.leanring.system.enrollment.EnrollmentStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
@CacheConfig(cacheNames = "paymentsCache")
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    @CacheEvict(allEntries = true)
    public Payment create(Payment payment) {
        Enrollment enrollment = payment.getEnrollment();
        checkPaymentStatus(enrollment, payment.getAmount());
        enrollmentRepository.save(enrollment);
        return paymentRepository.save(payment);
    }

    @Override
    @CachePut(key = "#id")
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("Payment", id)));
    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteById(Long id) {
        Payment payment = getById(id);
        Enrollment enrollment = payment.getEnrollment();

        BigDecimal remain = enrollment.getRemain().add(payment.getAmount());
        enrollment.setRemain(remain);

        if (remain.compareTo(enrollment.getTotal()) == 0) {
            enrollment.setStatus(UNPAID);

        } else if (remain.compareTo(enrollment.getTotal()) < 0) {
            enrollment.setStatus(PARTIAL);

        } else if (remain.compareTo(enrollment.getTotal()) > 0) {
            enrollment.setStatus(PAID);

        }

        enrollmentRepository.save(enrollment);
        paymentRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public PageDTO getAll(Map<String, String> params) {
        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        return new PageDTO(paymentRepository.findAll(pageable));
    }

    @Override
    public Page<Payment> findByEnrollmentId(Long enrollmentId, Map<String, String > params) {
        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        return paymentRepository.findByEnrollmentId(enrollmentId, pageable);
    }


    private void checkPaymentStatus(Enrollment enrollment, BigDecimal paidAmount) {
        BigDecimal cashback = BigDecimal.ZERO;
        BigDecimal remain = checkRemain(enrollment.getRemain());

        if (paidAmount.compareTo(remain) < 0) {
            remain = remain.subtract(paidAmount);

            enrollment.setStatus(PARTIAL);
            enrollment.setRemain(remain);
        } else if (paidAmount.compareTo(remain) > 0) {
            cashback = paidAmount.subtract(remain);

            enrollment.setStatus(PAID);
            enrollment.setRemain(BigDecimal.ZERO);
        } else {
            enrollment.setStatus(PAID);
            enrollment.setRemain(BigDecimal.ZERO);
        }
    }

    private BigDecimal checkRemain(BigDecimal remain) {
        if (remain.compareTo(BigDecimal.ZERO) == 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Cannot make payment because remain = 0");
        }

        return remain;
    }
}
