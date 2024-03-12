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
        String msg = "[DriivzServiceFeign#getChargerStatus] dmsTicket: 924e4cf7-a500-4f89-afa4-a0e68788c87e \n" +
                " --2024-03-11 16:47:26.211 | charger-service [e8fb57b6c79b7a066632c8d19de24cf1] -DEBUG 30760 --- [nio-8081-exec-1] c.v.e.e.c.w.a.a.d.DriivzServiceFeign     : [DriivzServiceFeign#getChargerStatus] ---> END HTTP (0-byte body) ";
        log.info(msg);


        StringBuilder stringBuilder = new StringBuilder("abc12345abc");
        stringBuilder.replace(2, 8, "*");

        log.info(stringBuilder.toString());


//        log.error("error",new NullPointerException("test"));
//        throw new RuntimeException("throw");

        return "ok";
    }
}
