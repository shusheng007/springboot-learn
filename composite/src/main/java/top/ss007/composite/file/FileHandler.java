package top.ss007.composite.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import java.io.File;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/8/27 16:21
 * @description: 文件相关
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class FileHandler {
    private static final String UPLOAD_URL = "https://xxx";
    private static final String AUTH_USER = "user";
    private static final String AUTH_PASSWORD = "password";


    private final RestTemplateBuilder restTemplateBuilder;

    public String uploadFile(File targetFile) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(targetFile));
        body.add("param", "your-param");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> response = restTemplateBuilder
                    .basicAuthentication(AUTH_USER, AUTH_PASSWORD)
                    .build()
                    .postForEntity(UPLOAD_URL, requestEntity, String.class);
            return response.getBody();
        } catch (RestClientException e) {
            log.error("upload file failed", e);
        }
        return "error";
    }
}
