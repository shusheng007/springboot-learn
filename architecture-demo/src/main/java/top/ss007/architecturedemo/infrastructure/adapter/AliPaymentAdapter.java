package top.ss007.architecturedemo.infrastructure.adapter;

import org.springframework.stereotype.Service;
import top.ss007.architecturedemo.domain.external.PaymentPort;
import top.ss007.architecturedemo.domain.valueobj.PaymentDetail;

@Service
public class AliPaymentAdapter implements PaymentPort {
    @Override
    public boolean payOrder(Long orderId, PaymentDetail paymentDetail) {
        //调用支付宝付API，或者是其sdk一顿支付
        return true;
    }
}
