package top.shusheng007.jpademo.service.impl;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        //当前用户id
        return Optional.of(1L);
    }
}
