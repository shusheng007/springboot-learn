package top.ss007.springlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.ss007.springlearn.config.*;

//@EnableConfigurationProperties({DatabaseConfig.class, ComplexConfig.class, ConversionConfig.class, ImmutablePresident.class})
@ConfigurationPropertiesScan()
@SpringBootApplication
public class SpringLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
    }

}
