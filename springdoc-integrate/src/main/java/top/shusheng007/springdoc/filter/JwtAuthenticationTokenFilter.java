package top.shusheng007.springdoc.filter;


import cn.hutool.json.JSONUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.shusheng007.springdoc.api.Result;
import top.shusheng007.springdoc.api.StatusCode;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";
    private static final String AUTH_HEADER_TYPE = "Bearer";


    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        log.info("request url:{}", requestURI);

        if (Pattern.matches("^/(swagger-ui|v3/api-docs).*", requestURI) ||
                List.of("/admin/login").contains(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }


        // get token from header [Authorization: Bearer <token>]
        String authHeader = request.getHeader(AUTH_HEADER);
        log.info("authHeader:{}", authHeader);
        if (Objects.isNull(authHeader) || !authHeader.startsWith(AUTH_HEADER_TYPE)) {
            genUnauthResponse(response);
            return;
        }

        String authToken = authHeader.split(" ")[1];
        if (!"123".equals(authToken)) {
            genUnauthResponse(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void genUnauthResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Result<Object> result = new Result<>(StatusCode.AUTH_UNAUTHORIZED, null);
        response.getWriter().write(JSONUtil.toJsonStr(result));
        response.getWriter().flush();
    }


}
