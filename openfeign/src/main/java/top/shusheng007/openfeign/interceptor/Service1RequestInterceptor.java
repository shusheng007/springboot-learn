package top.shusheng007.openfeign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Service1RequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        log.info("request url:{}", template.url());
        template.header("X-TRACE-ID", "666");
    }
}
