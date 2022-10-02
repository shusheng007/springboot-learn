package top.shusheng007.composite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
//@EnableConfigurationProperties({DatabaseConfig.class, ComplexConfig.class, ConversionConfig.class, ImmutablePresident.class})
@ConfigurationPropertiesScan()

public class CompositeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompositeApplication.class, args);
    }

}
