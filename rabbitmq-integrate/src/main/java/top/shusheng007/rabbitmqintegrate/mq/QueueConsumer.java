package top.shusheng007.rabbitmqintegrate.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/7/21 2:01 下午
 * @description:
 */

@Slf4j
@Service
public class QueueConsumer {

    @RabbitListener(queues = {"ss007"})
    public void receive(@Payload String fileBody) {
        log.info("ss007队列：" + fileBody);
    }


    @RabbitListener(queues = {"topicQueue1"})
    public void receiveTopic1(@Payload String fileBody) {
        log.info("topic1队列：" + fileBody);
    }

    @RabbitListener(queues = {"topicQueue2"})
    public void receiveTopic2(@Payload String fileBody) {
        log.info("topic2队列：" + fileBody);
    }



}
