package com.duelly.dtos.responses;

import com.duelly.dtos.UserDto;
import com.duelly.enums.UserRole;
import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private UserDto userInfo;
}
