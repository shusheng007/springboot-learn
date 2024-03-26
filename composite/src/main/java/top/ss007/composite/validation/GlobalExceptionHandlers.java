package top.ss007.composite.validation;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.ss007.composite.common.BaseResponse;

import java.text.MessageFormat;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> onBusinessException(BusinessException e) {
        return new BaseResponse<>(e.getCode(),
                MessageFormat.format("Invalid param: [{0}]", e.getMessage()), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errMsg = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ":" + f.getDefaultMessage())
                .collect(Collectors.joining("| "));

        return new BaseResponse<>(10000, MessageFormat.format("Invalid param: [{0}]", errMsg), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BaseResponse<String> onConstraintValidationException(ConstraintViolationException e) {
        String errMsg = e.getConstraintViolations().stream().map(f -> f.getPropertyPath().toString() + ":" + f.getMessage())
                .collect(Collectors.joining("| "));
        return new BaseResponse<>(10000, MessageFormat.format("Invalid param: [{0}]", errMsg), null);
    }


}
