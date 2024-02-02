package com.duelly.services.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtService {
    UserDetailsService userDetailsService();
}
