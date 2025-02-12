package top.ss007.composite.validation;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Integer code;
    private final String message;

    public BusinessException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
