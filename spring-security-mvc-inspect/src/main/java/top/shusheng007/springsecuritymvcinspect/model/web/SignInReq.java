package top.shusheng007.springsecuritymvcinspect.model.web;

import lombok.Data;

@Data
public class SignInReq {
    private String username;
    private String password;
}
