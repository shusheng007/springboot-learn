package top.ss007.scstream.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/7/24 17:48
 * @description:
 */

@Slf4j
@Configuration
public class MsgConsumer {

    @Bean
    public Consumer<MsgData> ss007AutoConsumer() {
        return new Consumer<MsgData>() {
            @Override
            public void accept(MsgData msgData) {
                log.info("ss007AutoConsumer接到消息：{}", msgData.toString());
            }
        };
    }

    @Bean
    public Consumer<String> ss007FuncConsumer() {
        return new Consumer<String>() {
            @Override
            public void accept(String msgData) {
                log.info("ss007FuncConsumer接到消息：{}", msgData);
            }
        };
    }

//    @Bean
//    public Consumer<String> ss007RouteConsumer() {
//        return new Consumer<String>() {
//            @Override
//            public void accept(String msgData) {
//                log.info("ss007RouteConsumer接到消息：{}", msgData);
//            }
//        };
//    }


}
