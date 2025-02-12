package top.ss007.readwritesplit.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import top.ss007.readwritesplit.multids.DataSourceSwitcher;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/2 10:57
 * @description:
 */
@Aspect
@Component
public class DataSourceAop {

    /**
     * 读切点
     */
    @Pointcut("@annotation(top.ss007.readwritesplit.anotation.Read)")
    public void readPointcut(){}

    /**
     * 写切点
     */
    @Pointcut("@annotation(top.ss007.readwritesplit.anotation.Write)")
    public void writePointcut(){}

    @Before("readPointcut()")
    public void beforeRead(){
        DataSourceSwitcher.useSlave();
    }

    @Before("writePointcut()")
    public void beforeWrite(){
        DataSourceSwitcher.useMaster();
    }
}
