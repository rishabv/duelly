package com.duelly.dtos.responses;

import lombok.Data;

@Data
public class RefreshResponse {
    public String refreshToken;
    public String token;
}
