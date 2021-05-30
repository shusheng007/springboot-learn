package top.ss007.springlearn.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/28 14:14
 * description:
 */
@Configuration
public class FilterConfiguration {

//    @Bean
//    public LogFilter logFilter(){
//        return new LogFilter();
//    }

    @Bean
    public FilterRegistrationBean<LogFilter> filterRegistrationBean(){
        FilterRegistrationBean registrationBean= new FilterRegistrationBean();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/filter/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
