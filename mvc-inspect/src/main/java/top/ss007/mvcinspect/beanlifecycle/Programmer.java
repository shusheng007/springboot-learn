package top.ss007.mvcinspect.beanlifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/5 21:28
 * @description:
 */

@Component
public class Programmer implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private JavaLang javaLang;

    public Programmer() {
        System.out.println("1.调用构造函数");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4.调用BeanFactoryAware扩展：" + beanFactory.getClass().getCanonicalName());

    }

    @Override
    public void setBeanName(String name) {
        System.out.println("3.调用BeanNameAware扩展：" + name);

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("9.调用DisposableBean扩展");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("7.调用InitializingBean扩展");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("5.调用ApplicationContextAware扩展：" + applicationContext.getClass().getCanonicalName());

    }


    @Autowired
    public void setJavaLang(JavaLang javaLang) {
        System.out.println("2.调用属性赋值方法：" + javaLang.getClass().getCanonicalName());
        this.javaLang = javaLang;
    }

    @PostConstruct
    public void myInit(){
        System.out.println("6.调用@PostConstruct注解的方法");
    }

    @PreDestroy
    public void myDestroy(){
        System.out.println("8.调用@PreDestroy注解的方法");

    }

    public void myInit2(){
        System.out.println("调用@Bean initMethod方法");
    }

    public void myDestroy2(){
        System.out.println("调用@Bean destroyMethod方法");

    }

}
