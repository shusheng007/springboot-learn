package top.ss007.log.cuslog;

import java.util.Arrays;

public enum HandlePolicy {
    REPLACE, DROP, UNKNOWN;


    public static HandlePolicy fromName(String inputName) {
        return Arrays.stream(HandlePolicy.values())
                .filter(p -> p.name().equalsIgnoreCase(inputName))
                .findFirst()
                .orElse(HandlePolicy.UNKNOWN);
    }
}
