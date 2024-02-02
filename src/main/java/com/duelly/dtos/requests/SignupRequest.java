package com.duelly.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank(message = "Full Name is required")
    private String fullName;
    @NotBlank(message = "Username is required")
    private String userName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Phone is required")
    private String phone;
    @NotBlank(message = "Password is required")
    private String password;
    private String country;
    private String state;
    private String city;
    @NotBlank(message = "Image is required")
    private String image;
    private String captchaToken;
    private boolean isSMSConsent;
}
