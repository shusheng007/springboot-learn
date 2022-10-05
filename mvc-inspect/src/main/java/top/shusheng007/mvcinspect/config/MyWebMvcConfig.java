package top.shusheng007.mvcinspect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.shusheng007.mvcinspect.interceptor.MyInterceptor;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/3 15:51
 * @description:
 */

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new MyInterceptor());
        ir.addPathPatterns("/mvc/*");
    }
}
