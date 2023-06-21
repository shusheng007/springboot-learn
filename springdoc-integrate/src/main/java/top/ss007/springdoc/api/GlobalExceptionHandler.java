package top.ss007.springdoc.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(HttpServletRequest httpServletRequest, Exception e) {
        log.error(httpServletRequest.getRequestURL().toString(), e);
        return new Result(StatusCode.FAILED.getCode(), StatusCode.FAILED.getMessage(), null);
    }

    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleBusinessException(HttpServletRequest httpServletRequest, ApiException e) {
        log.error("异常:{} 请求URL:{}", e.toString(), httpServletRequest.getRequestURL());
        return new Result(e.getCode(), e.getMessage(), null);
    }
}
