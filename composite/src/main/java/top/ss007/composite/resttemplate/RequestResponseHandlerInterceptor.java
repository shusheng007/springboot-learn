package top.ss007.composite.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class RequestResponseHandlerInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

//        request.getHeaders().add(BffConstant.X_REQUEST_ID, MDC.get(LogFilter.REQUEST_ID));

        return execution.execute(request, body);
    }
}
