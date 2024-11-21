package com.ecom.kafka;

import com.ecom.email.EmailService;
import com.ecom.kafka.order.OrderConformation;
import com.ecom.kafka.payment.PaymentConformation;
import com.ecom.notification.Notification;
import com.ecom.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.converter.GsonMessageConverter;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import static com.ecom.notification.NotificationType.ORDER_CONFIRMATION;
import static com.ecom.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;
import com.google.gson.Gson;
@Service
@Slf4j
public class NotificationConsumer {
    @Autowired
private NotificationRepository notificationRepository;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(String msg)throws MessagingException{
        Gson gson=new Gson();
       PaymentConformation paymentConformation= gson.fromJson(msg, PaymentConformation.class);
         log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConformation));
         notificationRepository.save(Notification.builder()
                         .notificationType(PAYMENT_CONFIRMATION)
                                 .notificationDate(LocalDateTime.now())
                                         .paymentConformation(paymentConformation)
                 . build());


    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(String msg) throws MessagingException {
        Gson gson=new Gson();
       OrderConformation orderConformation= gson.fromJson(msg, OrderConformation.class);
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConformation));
        notificationRepository.save(
                Notification.builder()
                        .notificationType(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConformation(orderConformation)
                        .build()
        );

    }
}
