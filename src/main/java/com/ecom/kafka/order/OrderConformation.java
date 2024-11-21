package com.ecom.kafka.order;

import com.ecom.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConformation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}
