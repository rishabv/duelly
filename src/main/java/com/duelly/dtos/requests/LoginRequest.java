package com.duelly.dtos.requests;

import com.duelly.constants.Validations;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = Validations.EMAIL_REQUIRED)
    private String email;
    @NotBlank(message = Validations.PASSWORD_REQUIRED)
    private String password;
}
