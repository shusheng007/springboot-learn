package top.shusheng007.springdoc.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(title = "统一模型",
        description = "API 统一返回值")
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    private String traceId;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.traceId = "";
    }

    public Result(StatusCode statusCode, T data) {
        this(statusCode.getCode(), statusCode.getMessage(), data);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public String getTraceId() {
        return traceId;
    }

    public static <E> Result<E> ok(E data) {
        return new Result<>(StatusCode.OK, data);
    }

    public static <E> Result<E> ok(StatusCode statusCode, E data) {
        return new Result<>(statusCode, data);
    }

    public static <E> Result<E> error() {
        return new Result<>(StatusCode.FAILED, null);
    }

}
