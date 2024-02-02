package com.duelly.services.UserService;

import com.duelly.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public User getUserInfo(String email);

    public User getUserInfo(Long id);
    UserDetails loadUserByUsername(String email);

    User validateUser(String email, String password);
}
