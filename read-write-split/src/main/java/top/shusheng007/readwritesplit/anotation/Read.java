package top.shusheng007.readwritesplit.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/1 10:32
 * @description:
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Read {
}
