package top.shusheng007.architecturedemo.domain.valueobj;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.shusheng007.architecturedemo.domain.enumerate.PayMethod;

@Getter
@RequiredArgsConstructor
public class PaymentDetail {
    private final String orderId;
    private final PayMethod payType;

}
