package top.shusheng007.openfeign.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.shusheng007.openfeign.config.Service1FeignConfig;
import top.shusheng007.openfeign.domain.FormRequest;

import java.util.Map;

/**
 * post form-url-encoded data
 */
@FeignClient(name = "s1-service", url = "http://s1-service", configuration = {Service1FeignConfig.class})
public interface Service1Feign {

    @PostMapping(value = "/formRequestWithMap", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String formRequestWithMap(Map<String, ?> request);

    @PostMapping(value = "/formRequestWithObj", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String formRequestWithObj(@RequestBody FormRequest request);

}
