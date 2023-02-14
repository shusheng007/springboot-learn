package top.ss007.springsecuritymvcinspect.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.ss007.springsecuritymvcinspect.model.Role;
import top.ss007.springsecuritymvcinspect.model.User;
import top.ss007.springsecuritymvcinspect.model.enumerate.RoleType;
import top.ss007.springsecuritymvcinspect.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByName(String userName) {
        if (!"shusheng007".equals(userName)) {
            throw new RuntimeException("用户不存在");
        }

//        List<Role> roles = List.of(new Role(RoleType.ADMIN), new Role(RoleType.USER));
        List<Role> roles = List.of( new Role(RoleType.USER));
        return new User(userName, passwordEncoder.encode("123456"), roles);
    }
}
