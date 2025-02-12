package top.ss007.architecturedemo.infrastructure.adapter;

import org.springframework.stereotype.Service;
import top.ss007.architecturedemo.domain.external.PaymentPort;
import top.ss007.architecturedemo.domain.valueobj.PaymentDetail;

@Service
public class WechatPaymentAdapter implements PaymentPort {

    @Override
    public boolean payOrder(Long orderId, PaymentDetail paymentDetail) {
        //调用微信支付API，或者是其sdk一顿支付
        return true;
    }
}
