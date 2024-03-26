package top.ss007.composite.common.config.configuration_annotaion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/27 16:59
 * description:
 */
@Configuration(proxyBeanMethods = false)
public class MyConfiguration {

    @Bean
    public Son son() {
        Son son = new Son();
        //Method annotated with @Bean is called directly in a @Configuration where proxyBeanMethods set to false.
        // Set proxyBeanMethods to true or use dependency injection.
//        son.setFather(father());
        return son;
    }

    @Bean
    public Father father() {
        return new Father();
    }

}
