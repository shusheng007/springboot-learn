package top.ss007.openfeign.log;

import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Slf4j
public class TunedSlf4jLogger extends Slf4jLogger {

    @Value("${cus-log.feign.request-headers:Authorization}")
    private List<String> includeRequestHeaders;

    @Value("${cus-log.feign.response-headers:}")
    private List<String> includeResponseHeaders;


    public TunedSlf4jLogger() {
        super(TunedSlf4jLogger.class);
    }


    @Override
    protected boolean shouldLogRequestHeader(String header) {
        return includeRequestHeaders != null && includeRequestHeaders.contains(header);
    }

    @Override
    protected boolean shouldLogResponseHeader(String header) {
        return includeResponseHeaders != null && includeResponseHeaders.contains(header);
    }
}
