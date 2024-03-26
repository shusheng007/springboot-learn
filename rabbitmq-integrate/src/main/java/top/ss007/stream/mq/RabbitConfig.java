package top.ss007.stream.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/7/21 2:28 下午
 * @description:
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue myQueue(){
        return new Queue("ss007",true);
    }


    @Bean
    public Queue topicQueue1(){
        return new Queue("topicQueue1",true);
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue("topicQueue2",true);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange())
                .with("ss007.王二狗.觉醒");
    }

    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange())
                .with("ss007.*.觉醒");
    }
}
