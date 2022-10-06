package top.shusheng007.mvcinspect;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import top.shusheng007.mvcinspect.beanlifecycle.Programmer;

@SpringBootApplication
public class MvcInspectApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MvcInspectApplication.class, args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MvcInspectApplication.class, args);
        Programmer programmer = (Programmer) applicationContext.getBean("programmer");
        applicationContext.close();
    }

}
