package top.shusheng007.rabbitmqintegrate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/7/21 2:31 下午
 * @description:
 */

@RequiredArgsConstructor
@Service
public class SendService {

   private final RabbitTemplate rabbitTemplate;
   private final Queue myQueue;

   public void sendMsg(String msg){
      rabbitTemplate.convertAndSend(myQueue.getName(),msg);
   }

   public void sendTopicMsg(String msg,String route){
      rabbitTemplate.convertAndSend("topicExchange",route,msg);
   }

}
