package top.shusheng007;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.shusheng007.springdoc.constant.Constant;

@SecurityScheme(name = Constant.API_TOKEN_SECURITY_SCHEMA, type = SecuritySchemeType.HTTP, scheme ="bearer", in = SecuritySchemeIn.HEADER)
@SecurityScheme(name = Constant.API_KEY_SECURITY_SCHEMA, type = SecuritySchemeType.APIKEY, paramName = "api-key", in = SecuritySchemeIn.HEADER)
@SpringBootApplication
public class SpringdocIntegrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdocIntegrateApplication.class, args);
    }

}
