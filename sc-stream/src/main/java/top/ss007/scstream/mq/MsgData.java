package top.ss007.scstream.mq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/7/24 17:39
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgData {
    private Integer id;
    private String content;
}
