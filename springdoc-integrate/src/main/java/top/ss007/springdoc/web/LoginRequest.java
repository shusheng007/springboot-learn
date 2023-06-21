package top.ss007.springdoc.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (C) 2023 ShuSheng007
 * <p>
 * Author ShuSheng007
 * Time 2023/6/20 22:55
 * Description
 */

@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
