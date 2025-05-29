package top.shusheng007.architecturedemo.domain.external;

import top.shusheng007.architecturedemo.domain.enumerate.PayMethod;

public interface PaymentGateway {
    PaymentPort getPayment(PayMethod type);
}
