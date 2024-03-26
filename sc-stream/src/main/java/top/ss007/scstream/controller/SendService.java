package top.ss007.scstream.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import top.ss007.scstream.mq.MsgData;

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

   public void sendMsg2Func(String msg,Integer id){
      streamBridgeTemplate.send("ss007Function-in-0",
              MessageBuilder.withPayload(MsgData.builder()
                      .id(id)
                      .content(msg)
                      .build())
                      .build());
   }

   public void sendMsg2Sup(String msg,Integer id){
      streamBridgeTemplate.send("ss007AutoProducer-out-0",
              MessageBuilder.withPayload(MsgData.builder()
                      .id(id)
                      .content(msg)
                      .build())
                      .build());
   }

   public void sendMsg2RouteConsumer(String msg, Integer id){
//      streamBridgeTemplate.send("functionRouter-in-0",
//              MessageBuilder.withPayload(MsgData.builder()
//                      .id(id)
//                      .content(msg)
//                      .build()).setHeader("type",id)
//                      .build());
   }

}
