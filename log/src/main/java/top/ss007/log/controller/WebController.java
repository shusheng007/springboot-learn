package top.ss007.log.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/log")
public class WebController {

    @GetMapping("/check")
    public String log() {
        log.info("============");
        log.info("key-value:{}","{\"userName\":\"shusheng007\", \"password\":\"Ab1234567\"}");
        log.info("phone number:{}","18712120001");



//        log.error("error",new NullPointerException("test"));
//        throw new RuntimeException("throw");

        return "ok";
    }
}
