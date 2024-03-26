package top.ss007.mvcinspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.ss007.mvcinspect.beanlifecycle.Programmer;

@SpringBootApplication
public class MvcInspectApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MvcInspectApplication.class, args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MvcInspectApplication.class, args);
        Programmer programmer = (Programmer) applicationContext.getBean("programmer");
        applicationContext.close();
    }

}
