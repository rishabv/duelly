package com.duelly.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.duelly.enums.UserRole;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class UserDto {
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String countryCode;
    private UserRole role;
    private String country;
    private String state;
    private String city;
    private int phone;
    private String provider;
    private String providerId;
    @JsonProperty("isRemoved")
    private boolean isRemoved;
    private String emailVerifyToken;
    private LocalDateTime tokenExpirationTimeEmail;
    @JsonProperty("isEmailVerified")
    private boolean isEmailVerified;
    private int phoneVerifyOtp;
    private String emailVerifyOtp;
    private LocalDateTime otpExpirationTimePhone;
    @JsonProperty("isPhoneVerified")
    private boolean isPhoneVerified;
    private boolean resetPasswordRequest;
    @JsonProperty("isVerify")
    private boolean isVerify;
    private List<String> connectionReqUserId;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    private int judgeRank = 0;
    private String token;
    private String refreshToken = "";
}
