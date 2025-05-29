package top.shusheng007.composite.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class IdempotentAspect {


    @Pointcut("@annotation(top.shusheng007.composite.aop.Idempotent)")
    public void invokePointcut() {
    }

    @Around("invokePointcut()")
    public Object invokeAround(ProceedingJoinPoint joinPoint) {

        try {

            Object result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return "";


    }


}
