package top.shusheng007.jpademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import top.shusheng007.jpademo.service.impl.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {

    @Bean
    public AuditorAware<Long> auditorAware(){
        return new AuditorAwareImpl();
    }


}
