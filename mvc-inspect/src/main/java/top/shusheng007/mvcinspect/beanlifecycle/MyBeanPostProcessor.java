package top.shusheng007.mvcinspect.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/6 15:42
 * @description:
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("programmer".equals(beanName)) {
            System.out.println("调用 BeanPostProcessor.postProcessBeforeInitialization() 方法,bean:" +
                    bean.getClass().getCanonicalName());
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("programmer".equals(beanName)) {
            System.out.println("调用 BeanPostProcessor.postProcessAfterInitialization() 方法,bean:" +
                    bean.getClass().getCanonicalName());
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
