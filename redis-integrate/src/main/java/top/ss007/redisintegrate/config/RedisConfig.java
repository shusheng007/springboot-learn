package top.ss007.redisintegrate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/7 11:50
 * @description:
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer keySerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        Jackson2JsonRedisSerializer<Object> valuesSerializer2 = new Jackson2JsonRedisSerializer(Object.class);

        template.setKeySerializer(RedisSerializer.string());
//        template.setValueSerializer(valuesSerializer2);
        template.setValueSerializer(valueSerializer);
//        template.setHashKeySerializer(keySerializer);
//        template.setHashValueSerializer(valueSerializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisScript<Boolean> moneyTransferScript(){
        Resource resource = new ClassPathResource("scripts/moneyTransfer.lua");
        return RedisScript.of(resource,Boolean.class);
    }
}
