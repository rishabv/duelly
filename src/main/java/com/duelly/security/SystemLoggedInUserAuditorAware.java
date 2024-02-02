package com.duelly.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.Optional;

@Component
public class SystemLoggedInUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        var user = CurrentUserThreadLocal.getCurrentUser();
        if(Objects.nonNull(user)) {
            return Optional.of(user.getUsername());
        } else {
            return Optional.ofNullable(null);
        }
    }
}