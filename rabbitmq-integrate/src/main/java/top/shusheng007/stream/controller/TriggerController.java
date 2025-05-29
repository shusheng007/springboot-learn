package top.shusheng007.stream.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/7/21 1:50 下午
 * @description:
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/trigger")
public class TriggerController {

    private final SendService sendService;

    @GetMapping("/send")
    public String sendMsgToMq(@RequestParam String msg){
        sendService.sendMsg(msg);
        return "ok";
    }

    @GetMapping("/sendTopic")
    public String sendTopicMsgToMq(@RequestParam String msg,@RequestParam String route){
        sendService.sendTopicMsg(msg,route);
        return "ok";
    }
}
