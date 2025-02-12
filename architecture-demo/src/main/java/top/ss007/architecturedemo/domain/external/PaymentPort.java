package top.ss007.architecturedemo.domain.external;

import top.ss007.architecturedemo.domain.valueobj.PaymentDetail;

public interface PaymentPort {

    boolean payOrder(Long orderId, PaymentDetail paymentDetail);
}
