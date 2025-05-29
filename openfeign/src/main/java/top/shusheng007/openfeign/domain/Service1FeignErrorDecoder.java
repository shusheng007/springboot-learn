package top.shusheng007.openfeign.domain;

import cn.hutool.core.io.IoUtil;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Service1FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        log.info("Service1FeignErrorDecoder.decode.methodKey:{} response:{}", methodKey, response.toString());

        try (InputStream is = response.body().asInputStream()) {
            String s = IoUtil.read(is, StandardCharsets.UTF_8);
            log.info("Service1FeignErrorDecoder.decode.body:{}", s);
        } catch (IOException e) {
            log.error("Service1FeignErrorDecoder.decode.error:", e);
        }

        return errorDecoder.decode(methodKey, response);
    }
}
