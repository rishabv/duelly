package com.duelly.controllers;

import com.duelly.constants.RestApiConstant;
import com.duelly.dtos.requests.LoginRequest;
import com.duelly.dtos.requests.VerifyUserRequest;
import com.duelly.dtos.responses.BaseApiResponse;
import com.duelly.dtos.responses.LoginResponse;
import com.duelly.entities.BaseEntity;
import com.duelly.entities.User;
import com.duelly.services.UserService.UserService;
import com.duelly.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.*;
import com.duelly.dtos.requests.SignupRequest;
import com.duelly.dtos.responses.SignupResponse;
import com.duelly.services.auth.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(RestApiConstant.BASE_URL + RestApiConstant.AUTH_URL)
public class AuthController {
    private final AuthService authService;

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping(RestApiConstant.SIGNUP)
    public ResponseEntity<SignupResponse<?>> signupUser(@Valid @ModelAttribute SignupRequest signupRequest){
        UserDto createdUser = authService.createUser(signupRequest);
        log.info("Here is the password"+signupRequest.getPassword());
        if(createdUser==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SignupResponse<>("User not created"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new SignupResponse<>(createdUser, SuccessMessage.ACCOUNT_CREATED));
    }

    @PostMapping(RestApiConstant.LOGIN)
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        UserDto user = authService.loginUser(loginRequest);
        LoginResponse response = new LoginResponse();
        response.setUserInfo(user);
        response.setMessage(SuccessMessage.LOGGED_IN);
        return response;
    }

    @PutMapping(RestApiConstant.VERIFY)
    public ResponseEntity<BaseApiResponse<?>> verifyUser(@Valid VerifyUserRequest request){
    return null;
    }
}
