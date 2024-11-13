package com.sam.payment.notification;


import com.sam.payment.model.PaymentMethods;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethods paymentMethods,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
