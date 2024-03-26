package top.ss007.composite.resttemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class VecrRequestResponseHandlerInterceptor implements ClientHttpRequestInterceptor {
    private static final String REQUEST_META = "vecr-request-meta";
    private static final String HTTP_HEADER_API_KEY = "api-key";


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info("vecr restTemplate request param: {}", new String(body, StandardCharsets.UTF_8));

//        request.getHeaders().add(BffConstant.X_REQUEST_ID, MDC.get(LogFilter.REQUEST_ID));
//        request.getHeaders().add(REQUEST_META, UserContext.getRequestMeta());

        request.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return execution.execute(request, body);
    }
}
