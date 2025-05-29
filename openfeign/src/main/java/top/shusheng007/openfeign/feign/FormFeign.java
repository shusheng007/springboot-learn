package top.shusheng007.openfeign.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.shusheng007.openfeign.config.FormFeignConfig;
import top.shusheng007.openfeign.domain.FormRequest;

import java.util.Map;

/**
 * post form-url-encoded data
 */
@FeignClient(name = "form-service", url = "http://form-service", configuration = {FormFeignConfig.class})
public interface FormFeign {

    @PostMapping(value = "/formRequestWithMap", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String formRequestWithMap(Map<String, ?> request);

    @PostMapping(value = "/formRequestWithObj", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String formRequestWithObj(@RequestBody FormRequest request);

}
