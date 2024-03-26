package top.ss007.log.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class WebController {

    @GetMapping("/check")
    public String log() {
        log.info("======{\"username\":\"shusheng007\",\"password\":\"abcdefg1234567\"}======");

        return "ok";
    }
}
