package top.ss007.architecturedemo.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.ss007.architecturedemo.domain.enumerate.PayMethod;
import top.ss007.architecturedemo.domain.external.PaymentGateway;
import top.ss007.architecturedemo.domain.external.PaymentPort;


@RequiredArgsConstructor
@Component
public class PaymentGatewayImpl implements PaymentGateway {
    private final AliPaymentAdapter aliPaymentAdapter;
    private final WechatPaymentAdapter wechatPaymentAdapter;

    public PaymentPort getPayment(PayMethod type) {
        return switch (type) {
            case ALIPAY -> aliPaymentAdapter;
            case WECHAT_PAY -> wechatPaymentAdapter;
            default -> throw new UnsupportedOperationException();
        };

    }

}
