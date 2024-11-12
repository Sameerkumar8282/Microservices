package com.sameer.order.DTO;

import com.sameer.order.model.PaymentMode;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMode paymentMode,
        String customerId
) {
}
