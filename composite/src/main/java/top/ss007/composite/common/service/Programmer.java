package top.ss007.composite.common.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Programmer implements CommandLineRunner {

    private final String content = "码字如飞";

    public String program() {
        return content;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("current thread id:{} name:{}", Thread.currentThread().getId(), Thread.currentThread().getName());
    }
}
