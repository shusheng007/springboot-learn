package top.ss007.architecturedemo.domain.external;

import top.ss007.architecturedemo.domain.enumerate.PayMethod;

public interface PaymentGateway {
    PaymentPort getPayment(PayMethod type);
}
