package top.shusheng007.composite.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        log.info("get http raw status code: {}, status code:{}", response.getRawStatusCode(), response.getStatusCode());
        return response.getStatusCode().is5xxServerError() || response.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS;
    }
}
