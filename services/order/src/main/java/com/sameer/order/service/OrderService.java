package com.sameer.order.service;

import com.sameer.order.DTO.*;
import com.sameer.order.client.CustomerClient;
import com.sameer.order.client.ProductClient;
import com.sameer.order.exception.BusinessException;
import com.sameer.order.kafka.OrderProducer;
import com.sameer.order.mapper.OrderMapper;
import com.sameer.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public Integer createOrder(@Valid OrderRequest request) {
        //Check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException(String.format("Cannot create Order :: No Customer exists with provide Id %s", request.customerId())));

        //purchase the products --> product-ms(RestTemplate)

       var purchaseProducts =  this.productClient.purchaseProducts(request.products());

        //persist order

        var order = this.repository.save(mapper.toOrder(request));

        //persist order lines

        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }

        //todo start Payment process


        //send the order confirmation --> notification-ms(kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMode(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return  repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No Order found with this Id %d",orderId)));
    }
}
