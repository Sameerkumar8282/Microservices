package com.sam.payment.DTO;

import com.sam.payment.model.PaymentMethods;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethods paymentMethods,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
