package com.kongsun.leanring.system.payment;

import com.kongsun.leanring.system.enrollment.Enrollment;
import com.kongsun.leanring.system.enrollment.EnrollmentRepository;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.kongsun.leanring.system.enrollment.EnrollmentStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public PaymentResponse create(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.toPayment(paymentDTO);
        Enrollment enrollment = payment.getEnrollment();

        BigDecimal change = BigDecimal.ZERO;
        if (paymentDTO.getAmount().compareTo(enrollment.getRemain()) < 0) {
            enrollment.setStatus(PARTIAL);
            enrollment.setRemain(enrollment.getRemain().subtract(paymentDTO.getAmount()));
        } else if (paymentDTO.getAmount().compareTo(enrollment.getRemain()) == 0) {
            enrollment.setStatus(PAID);
            enrollment.setRemain(BigDecimal.ZERO);
        } else {
            enrollment.setStatus(PAID);
            enrollment.setRemain(BigDecimal.ZERO);
            change = paymentDTO.getAmount().subtract(enrollment.getRemain());
        }

        enrollmentRepository.save(enrollment);
        paymentRepository.save(payment);

        PaymentResponse response = paymentMapper.toPaymentResponse(payment);
        response.setChange(change);
        return response;
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("Payment", id)));
    }

    @Override
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
}
