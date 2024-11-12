package com.sameer.order.mapper;

import com.sameer.order.DTO.OrderRequest;
import com.sameer.order.DTO.OrderResponse;
import com.sameer.order.model.Order;

public class OrderMapper {
    public Order toOrder(OrderRequest request){
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMode(request.paymentMode())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMode(),
                order.getCustomerId()
        );
    }
}
