package com.sameer.order.mapper;

import com.sameer.order.DTO.OrderLineRequest;
import com.sameer.order.DTO.OrderLineResponse;
import com.sameer.order.model.Order;
import com.sameer.order.model.OrderLine;

public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
