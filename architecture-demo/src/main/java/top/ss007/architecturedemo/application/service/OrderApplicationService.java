package top.ss007.architecturedemo.application.service;

import top.ss007.architecturedemo.application.dto.OrderCreateDto;
import top.ss007.architecturedemo.domain.aggregate.Order;
import top.ss007.architecturedemo.domain.valueobj.PaymentDetail;

public interface OrderApplicationService {
    Order createOrder(OrderCreateDto orderCreateDto);

    Order completeOrder(Long orderId, PaymentDetail paymentDetail);
}
