package top.shusheng007.springdoc.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.shusheng007.springdoc.constant.Constant;

import java.util.List;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("程序员API")
                        .description("程序员的大本营")
                        .version("v1.0.0")
                        .license(new License()
                                .name("许可协议")
                                .url("https://shusheng007.top"))
                        .contact(new Contact()
                                .name("书生007邮箱")
                                .email("wangben850115@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("ShuSheng007博客")
                        .url("https://shusheng007.top"))
                .security(List.of(new SecurityRequirement().addList(Constant.API_TOKEN_SECURITY_SCHEMA),
                        new SecurityRequirement().addList(Constant.API_KEY_SECURITY_SCHEMA)));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/admin/**")
                .build();
    }

}
