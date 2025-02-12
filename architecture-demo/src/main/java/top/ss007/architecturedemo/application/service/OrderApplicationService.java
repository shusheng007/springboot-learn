package top.ss007.architecturedemo.application.service;

import top.ss007.architecturedemo.domain.valueobj.PaymentDetail;

public interface OrderApplicationService {
    void completeOrder(Long orderId, PaymentDetail paymentDetail);
}
