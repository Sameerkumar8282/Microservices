package com.sam.payment.service;

import com.sam.payment.DTO.PaymentRequest;
import com.sam.payment.mapper.Mapper;
import com.sam.payment.notification.NotificationProducer;
import com.sam.payment.notification.PaymentNotificationRequest;
import com.sam.payment.repository.PaymentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final Mapper mapper;
    private final NotificationProducer producer;

    public Integer createPayment(@Valid PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
        producer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethods(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
                );
        return payment.getId();
    }
}
