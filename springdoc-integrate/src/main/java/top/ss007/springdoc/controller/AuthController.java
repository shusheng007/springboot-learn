package top.ss007.springdoc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ss007.springdoc.api.Result;
import top.ss007.springdoc.constant.Constant;
import top.ss007.springdoc.web.LoginRequest;

/**
 * Copyright (C) 2023 ShuSheng007
 * <p>
 * Author ShuSheng007
 * Time 2023/6/20 22:53
 * Description
 */

@Tag(name = "权限模块", description = "认证与授权")
@RestController
@RequestMapping(value = "/admin", produces = "application/json")
public class AuthController {

    @Operation(description = "登录")
    @SecurityRequirements(value = {
            @SecurityRequirement(name = Constant.API_KEY_SECURITY_SCHEMA)
    })
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginRequest request) {
        return Result.ok("123");
    }
}
