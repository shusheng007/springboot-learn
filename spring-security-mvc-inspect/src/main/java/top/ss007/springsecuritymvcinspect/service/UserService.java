package top.ss007.springsecuritymvcinspect.service;

import top.ss007.springsecuritymvcinspect.model.User;

public interface UserService {
    User getUserByName(String userName);
}
