package top.shusheng007.springdoc.api;


public enum StatusCode {
    OK(0, "成功"),
    FAILED(1, "未知错误"),

    AUTH_UNAUTHORIZED(401, "un auth"),

    ;

    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
