package top.shusheng007.composite.filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/28 13:55
 * description:
 */

@Order(2)
public class LogFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("LogFilter请求url：{} | param:{}", request.getRequestURL(),request.getQueryString());

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
