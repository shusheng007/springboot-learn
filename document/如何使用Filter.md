三种方法使Filter生效

# 创建Filter类
实现Filter接口即可

```
@Order(1)
public class AuthFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("AuthFilter请求url：{}", request.getRequestURL());

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
```
因为我们可以设置多个Filter，所以他们之间就会有个排序问题，我们可以使用`@Order`修饰我们的Filter来设置顺序，例如`@Order(1)`表示最先执行

- 使用`@Component`标记我们的Filter类即可

- 在Configuration类里面使用`@Bean`返回`Filter`实例

```
@Configuration
public class FilterConfiguration {
   @Bean
   public LogFilter logFilter(){
       return new LogFilter();
   }
}
```
- 在Configuration类里面使用`@Bean`返回`FilterRegistrationBean`实例
```
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<LogFilter> filterRegistrationBean(){
        FilterRegistrationBean registrationBean= new FilterRegistrationBean();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/filter/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
```

前两种不太灵活，他们将拦截所有的请求，而第三种可以选择拦截特定的请求。还有一点需要注意的是第三种方法`@Order`注解就失效了，必须
主动设置此Filter的顺序，如上文所示。
