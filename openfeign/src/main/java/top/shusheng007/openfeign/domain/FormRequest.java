package top.shusheng007.openfeign.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormRequest {
    private String clientId;
    private String clientSecret;
}
