package com.duelly.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupResponse<T> extends BaseApiResponse<T> {
    T result;

    List<ErrorDto> errors;
    public SignupResponse(T data, String message) {
        super(message);
        this.result = data;
    }
    public SignupResponse(String message){
        super(message);
    }
}
