package top.ss007.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OpenfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignApplication.class, args);
    }

}
