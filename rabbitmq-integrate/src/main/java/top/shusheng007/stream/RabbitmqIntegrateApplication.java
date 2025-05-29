package top.shusheng007.stream;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class RabbitmqIntegrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqIntegrateApplication.class, args);
    }

}
