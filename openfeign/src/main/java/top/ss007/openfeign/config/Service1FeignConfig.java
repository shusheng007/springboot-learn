package top.ss007.openfeign.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import top.ss007.openfeign.domain.Service1FeignErrorDecoder;
import top.ss007.openfeign.interceptor.Service1RequestInterceptor;

public class Service1FeignConfig {

    @Bean
    public RequestInterceptor userFeignRequestInterceptor() {
        return new Service1RequestInterceptor();
    }


    @Bean
    public ErrorDecoder userFeignErrorDecoder() {
        return new Service1FeignErrorDecoder();
    }
}
