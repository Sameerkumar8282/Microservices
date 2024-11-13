package com.sam.payment.mapper;

import com.sam.payment.DTO.PaymentRequest;
import com.sam.payment.model.Payment;
import jakarta.validation.Valid;

public class Mapper {
    public Payment toPayment(@Valid PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethods(request.paymentMethods())
                .amount(request.amount())
                .build();
    }
}
