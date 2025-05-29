package top.shusheng007.springsecuritymvcinspect.service;

import top.shusheng007.springsecuritymvcinspect.model.User;

public interface UserService {
    User getUserByName(String userName);
}
