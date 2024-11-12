package com.sameer.order.DTO;

import com.sameer.order.model.PaymentMode;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
   String orderReference,
   BigDecimal totalAmount,
   PaymentMode paymentMode,
   CustomerResponse customerResponse,
   List<PurchaseResponse> products
) {
}
