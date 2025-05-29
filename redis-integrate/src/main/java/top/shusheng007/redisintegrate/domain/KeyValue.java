package top.shusheng007.redisintegrate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/7 18:29
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue implements Serializable {
    private String key;
    private Object value;
}
