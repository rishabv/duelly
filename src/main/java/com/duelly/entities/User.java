package com.duelly.entities;

import com.duelly.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.duelly.entities.RefreshToken;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String countryCode;
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;
    private String country;
    private String state;
    private String city;
    private String phone;
    private String provider;
    private String providerId;
    @JsonProperty("isRemoved")
    private boolean isRemoved;
    private String emailVerifyToken;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime tokenExpirationTimeEmail;
    @JsonProperty("isEmailVerified")
    private boolean isEmailVerified;
    private int phoneVerifyOtp;
    private String emailVerifyOtp;
    @Temporal(TemporalType.TIMESTAMP)
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
    @OneToOne
    private RefreshToken refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
