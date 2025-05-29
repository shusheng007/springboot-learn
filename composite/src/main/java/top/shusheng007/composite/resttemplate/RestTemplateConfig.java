package top.shusheng007.composite.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
public class RestTemplateConfig {

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @Autowired
    private RequestResponseHandlerInterceptor requestResponseHandlerInterceptor;

    @Autowired
    private VecrRequestResponseHandlerInterceptor vecrRequestResponseHandlerInterceptor;

    @Bean("vecrRestTemplate")
    public RestTemplate vecrRestTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.setConnectTimeout(Duration.ofSeconds(60))
                .setReadTimeout(Duration.ofSeconds(60))
                .additionalInterceptors(Collections.singletonList(vecrRequestResponseHandlerInterceptor))
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }


    //    @Primary
//    @Bean("commonRestTemplate")
    public RestTemplate commonRestTemplate(RestTemplateBuilder builder) {
        builder = builder.setConnectTimeout(Duration.ofSeconds(60))
                .setReadTimeout(Duration.ofSeconds(60))
                .additionalInterceptors(Collections.singletonList(requestResponseHandlerInterceptor));

        if (List.of("dev").contains(activeProfile)) {
            builder = builder.setConnectTimeout(Duration.ofSeconds(600))
                    .setReadTimeout(Duration.ofSeconds(600));
            try {
                TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

                SSLContext sslContext = SSLContexts.custom()
                        .loadTrustMaterial(null, acceptingTrustStrategy)
                        .build();

                SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

                CloseableHttpClient httpClient = HttpClients.custom()
                        .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                                .setSSLSocketFactory(csf)
                                .build())
                        .build();

                HttpComponentsClientHttpRequestFactory requestFactory =
                        new HttpComponentsClientHttpRequestFactory();

                requestFactory.setHttpClient(httpClient);

                builder = builder.requestFactory(() -> requestFactory);
            } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
                log.error("ignore certificate failed", e);
            }
        }


        RestTemplate restTemplate = builder
                .build();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }


}
