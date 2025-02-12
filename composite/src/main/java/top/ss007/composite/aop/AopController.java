package top.ss007.composite.aop;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/aop")
public class AopController {

    @PostMapping("/run")
    public String testAop(@RequestBody AopRequest request) {

        return "ok";
    }
}
