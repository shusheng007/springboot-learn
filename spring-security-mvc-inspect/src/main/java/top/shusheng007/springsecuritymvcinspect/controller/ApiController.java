package top.shusheng007.springsecuritymvcinspect.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/users/{id}")
    public String getUserDetail(@PathVariable String id){
        return "用户详情:" + id;
    }


}
