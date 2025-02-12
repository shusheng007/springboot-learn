package top.ss007.architecturedemo.domain.service;

import top.ss007.architecturedemo.domain.valueobj.PaymentDetail;

public interface OrderPaymentService {
    boolean processPayment(Long orderId, PaymentDetail paymentDetail);
}
