package top.shusheng007.redisintegrate.run;

import lombok.Data;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/7 18:29
 * @description:
 */
@Data
public class KeyValue {
    private String key;
    private Object value;
}
