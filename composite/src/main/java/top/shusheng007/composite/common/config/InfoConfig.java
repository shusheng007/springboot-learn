package top.shusheng007.composite.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/12 15:34
 * description:
 */

@Configuration
@PropertySource(value = "properties/info.properties",ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "test")
public class InfoConfig {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getTest() {
        return name;
    }
}
