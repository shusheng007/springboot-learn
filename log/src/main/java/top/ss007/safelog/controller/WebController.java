package top.ss007.safelog.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/log")
public class WebController {
    private final Logger log = LoggerFactory.getLogger(WebController.class);


    @PostMapping("/check")
    public String log(@RequestBody String input) {
        Marker marker = MarkerFactory.getMarker("PLAIN1");

        log.info(marker,"不脱敏:{}", input);
        log.info("脱敏:{}",input);

        return "ok";
    }


}
