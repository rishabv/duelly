package com.duelly.services.auth;

import com.duelly.dtos.requests.LoginRequest;
import com.duelly.dtos.requests.SignupRequest;
import com.duelly.dtos.UserDto;
import com.duelly.dtos.requests.VerifyUserRequest;
import com.duelly.entities.User;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

    UserDto loginUser(LoginRequest loginRequest);

    UserDto verify(VerifyUserRequest request, User user);
}
