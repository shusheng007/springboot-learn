package top.ss007.scstream.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/7/24 17:37
 * @description: 模拟消息生产微服务
 */
@Slf4j
@Configuration
public class MsgProducer {

    private int id = 1;

    //消息自动生成器
    @Bean
    public Supplier<Message<MsgData>> ss007AutoProducer() {

        return  new Supplier<Message<MsgData>>() {
            @Override
            public Message<MsgData> get() {
                log.info("发送第{}次条消息：",id);
                return MessageBuilder.withPayload(MsgData.builder()
                        .id(id++)
                        .content("我爱你牛翠华")
                        .build())
                        .build();
            }
        };

    }

    //从其他服务接收消息，然后发送给另一个服务
    @Bean
    public Function<Message<MsgData>,Message<String>> ss007Function(){
        return new Function<Message<MsgData>, Message<String>>() {
            @Override
            public Message<String> apply(Message<MsgData> msg) {
                MsgData payload = msg.getPayload();
                return MessageBuilder.withPayload(String.format("王二狗第%d说：%s", payload.getId(),payload.getContent()))
                        .build();
            }
        };
    }


}
