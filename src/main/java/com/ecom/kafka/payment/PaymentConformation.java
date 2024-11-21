package com.ecom.kafka.payment;

import java.math.BigDecimal;

public record PaymentConformation(String orderReference,
                                  BigDecimal amount,
                                  PaymentMethod paymentMethod,
                                  String customerFirstname,
                                  String customerLastname,
                                  String customerEmail) {
}
