package com.duelly.dtos.requests;

import com.duelly.constants.Validations;
import jakarta.validation.constraints.NotBlank;

public class RefreshRequest {
    @NotBlank(message = Validations.EMAIL_REQUIRED)
    public String refreshToken;
    public String token;

}
