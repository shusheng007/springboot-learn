package top.ss007.architecturedemo.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.ss007.architecturedemo.domain.aggregate.Order;
import top.ss007.architecturedemo.domain.external.repository.OrderRepository;
import top.ss007.architecturedemo.domain.service.OrderPaymentService;
import top.ss007.architecturedemo.domain.valueobj.PaymentDetail;

@RequiredArgsConstructor
@Service
public class OrderPaymentServiceImpl implements OrderPaymentService {
    private final OrderRepository orderRepository;

    @Override
    public boolean processPayment(Long orderId, PaymentDetail paymentDetail) {
        // 获取订单
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.payOrder();
        orderRepository.save(order);
        return true;
    }
}
