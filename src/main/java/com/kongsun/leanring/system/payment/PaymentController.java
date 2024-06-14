package com.kongsun.leanring.system.payment;

import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid PaymentDTO paymentDTO) {
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(paymentService.create(paymentDTO))
                        .message("create payment successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @DeleteMapping("{paymentId}g")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long paymentId) {
        paymentService.deleteById(paymentId);
        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete payment successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }


}
