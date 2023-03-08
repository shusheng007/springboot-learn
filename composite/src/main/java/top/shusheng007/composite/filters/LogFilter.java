package top.shusheng007.composite.filters;


import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
@Order(1)
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        log.info("LogFilter请求url：{} | param:{}", request.getRequestURL(),request.getQueryString());

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
