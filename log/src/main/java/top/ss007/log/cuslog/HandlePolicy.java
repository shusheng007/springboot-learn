package top.ss007.log.cuslog;

import java.util.Arrays;

public enum HandlePolicy {
    REPLACE, DROP;


    public static HandlePolicy fromName(String inputName) {
        return Arrays.stream(HandlePolicy.values())
                .filter(p -> p.name().equalsIgnoreCase(inputName))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("invalid param, only support REPLACE and DROP"));
    }
}
