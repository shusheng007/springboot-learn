package top.shusheng007.mvcinspect.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/6 15:43
 * @description:
 */

@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("programmer".equals(beanName)) {
            System.out.println("调用 InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation() 方法,bean:" +
                    beanClass.getCanonicalName());
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("programmer".equals(beanName)) {
            System.out.println("调用 InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation() 方法,bean:" +
                    bean.getClass().getCanonicalName());
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("programmer".equals(beanName)) {
            System.out.println("调用 InstantiationAwareBeanPostProcessor.postProcessProperties() 方法,bean:" +
                    bean.getClass().getCanonicalName() +
                    " values:" + Arrays.toString(pvs.getPropertyValues()));
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("programmer".equals(beanName)) {
            System.out.println("调用 InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization() 方法,bean:" +
                    bean.getClass().getCanonicalName());
        }

        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("programmer".equals(beanName)) {
            System.out.println("调用 InstantiationAwareBeanPostProcessor.postProcessAfterInitialization() 方法,bean:" +
                    bean.getClass().getCanonicalName());
        }

        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
