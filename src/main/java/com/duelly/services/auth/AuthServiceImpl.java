package com.duelly.services.auth;

import com.duelly.constants.ErrorMessages;
import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.requests.LoginRequest;
import com.duelly.dtos.requests.RefreshRequest;
import com.duelly.dtos.requests.SignupRequest;
import com.duelly.dtos.responses.BaseApiResponse;
import com.duelly.dtos.responses.LoginResponse;
import com.duelly.dtos.responses.RefreshResponse;
import com.duelly.enums.UserRole;
import com.duelly.dtos.UserDto;
import com.duelly.dtos.requests.VerifyUserRequest;
import com.duelly.entities.User;
import com.duelly.repositories.UserRepository;
import com.duelly.services.UserService.UserService;
import com.duelly.util.JwtUtil;
import com.duelly.util.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    @Autowired
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final Utils utils;

    @Override
    public UserDto createUser(SignupRequest signupRequest){
        Boolean isExist = userRepository.existsByEmail(signupRequest.getEmail());
        if(isExist) {
            throw new IllegalArgumentException("User already exist");
        }
        User user = new User();
        BeanUtils.copyProperties(signupRequest, user);
        user.setPhone(signupRequest.getPhone());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(UserRole.USER);
        log.info("saving password" + user.getPassword());
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        final int phoneOtp = utils.generateOtp();
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime newDateTime = date.plus(24, ChronoUnit.HOURS);
        user.setOtpExpirationTimePhone(newDateTime);
        user.setPhoneVerifyOtp(phoneOtp);
        User createdUser = userRepository.save(user);

        final UserDetails userDetails = userService.loadUserByUsername(signupRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        userDto.setToken(token);
        userDto.setPhoneVerifyOtp(phoneOtp);
        log.info("created user with email : " + createdUser.getEmail());
        return userDto;
    }

    @Override
    public UserDto loginUser(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username or password");
        } catch (DisabledException e){
            throw new BadCredentialsException("User is disabled");
        }
        final User userDetails = userService.getUserInfo(loginRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        UserDto userDto = new UserDto();
        userDto.setToken(token);
        BeanUtils.copyProperties(userDetails, userDto);
       return userDto;
    }

    @Override
    public RefreshResponse getRefreshToken(String token){
        String userEmail = jwtUtil.extractUsername(token);
        UserDetails userDetails = userService.loadUserByUsername(userEmail);
        if(jwtUtil.isTokenValid(token, userDetails)){
            RefreshResponse res = new RefreshResponse();
            final String newToken = jwtUtil.generateToken(userDetails);
            res.setRefreshToken(token);
            res.setToken(newToken);
            return res;
        } else {
            throw new IllegalArgumentException(ErrorMessages.SESSION_EXPIRED);
        }
    }

    @Override
    public UserDto verify(VerifyUserRequest request, User user){
        log.info("logged in user details, " + user.getId());
        Optional<User> loggedInUser = userRepository.findById(user.getId());
        if(!loggedInUser.isPresent()){
            throw new RuntimeException("Invalid user login");
        }
        System.out.println(request.getOtp() + " " + user.getPhoneVerifyOtp());
        this.verifyUser(request.getOtp(), user.getPhoneVerifyOtp(), user.getOtpExpirationTimePhone());
        user.setVerify(true);
        user.setPhoneVerified(true);
        userRepository.save(user);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        String token = jwtUtil.generateToken(user);
        userDto.setToken(token);
        return userDto;
    }

    private void verifyUser(int requestOtp, int otp, LocalDateTime tokenExpirationTimePhone){
        var now = LocalDateTime.now();
        if(!tokenExpirationTimePhone.isBefore(now)){
            throw new IllegalArgumentException("Otp time has been exceeded.Please resend the otp");
        }
        if(requestOtp != otp) {
            log.info(requestOtp + " is differ from " + otp);
            throw new IllegalArgumentException("Invalid Otp");
        }
    }
}
