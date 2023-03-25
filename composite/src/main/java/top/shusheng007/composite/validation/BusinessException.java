package top.shusheng007.composite.validation;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;

    public BusinessException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
