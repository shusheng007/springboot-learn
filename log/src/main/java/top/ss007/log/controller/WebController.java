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
        log.info("============");
        log.info("-------------123456789abcdefg--------------");

//        log.error("error",new NullPointerException("test"));
//
//        throw new RuntimeException("throw");

        return "ok";
    }
}
