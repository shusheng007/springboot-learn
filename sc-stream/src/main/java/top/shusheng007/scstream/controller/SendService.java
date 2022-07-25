package top.shusheng007.scstream.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import top.shusheng007.scstream.mq.MsgData;

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

   private final StreamBridge streamBridgeTemplate;

   public void sendMsg(String msg,Integer id){
      streamBridgeTemplate.send("ss007Consumer-in-0",
              MessageBuilder.withPayload(MsgData.builder()
                      .id(id)
                      .content(msg)
                      .build())
                      .build());

      streamBridgeTemplate.send("ss007Function1-in-0",
              MessageBuilder.withPayload(MsgData.builder()
                      .id(id)
                      .content(msg)
                      .build())
                      .build());
   }

}
