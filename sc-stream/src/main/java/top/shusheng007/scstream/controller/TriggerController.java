package top.shusheng007.scstream.controller;

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


    @GetMapping("/sendTopic")
    public String sendTopicMsgToMq(@RequestParam String msg,@RequestParam Integer id){
        sendService.sendMsg(msg,id);
        return "ok";
    }
}
