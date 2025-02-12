package top.ss007.architecturedemo.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.ss007.architecturedemo.application.service.OrderApplicationService;
import top.ss007.architecturedemo.domain.external.PaymentPort;
import top.ss007.architecturedemo.domain.service.OrderDispatchService;
import top.ss007.architecturedemo.domain.service.OrderPaymentService;
import top.ss007.architecturedemo.domain.valueobj.PaymentDetail;

@RequiredArgsConstructor
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderPaymentService orderPaymentService;
    private final OrderDispatchService orderDispatchService;

    private final PaymentPort paymentPort;

    @Override
    public void completeOrder(Long orderId, PaymentDetail paymentDetail) {
        boolean isOk = paymentPort.payOrder(orderId, paymentDetail);
        if (!isOk) {
            return;
        }
        // 调用支付服务
        orderPaymentService.processPayment(orderId, paymentDetail);
        // 调用发货服务
        orderDispatchService.dispatchOrder(orderId);
    }
}
