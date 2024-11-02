package com.sam.microservice.order.DTO;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber , String skuCode, BigDecimal price,Integer quantity) {
}
