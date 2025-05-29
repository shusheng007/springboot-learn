package top.shusheng007.springsecuritymvcinspect.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.shusheng007.springsecuritymvcinspect.model.User;
import top.shusheng007.springsecuritymvcinspect.service.UserService;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);
        return new UserDetailsImpl(user);
    }
}


