package top.shusheng007.architecturedemo.domain.enumerate;

import java.util.Arrays;

public enum PayMethod {
    ALIPAY, WECHAT_PAY, UNKNOWN;

    public static PayMethod fromName(String alias) {
        return Arrays.stream(values())
                .filter(v -> v.name()
                        .equalsIgnoreCase(alias))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
