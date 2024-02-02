package com.duelly.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {
    private String msg;
    private String code;
    private Object rejectedValue;
    private String stackTrace;

    public ErrorDto(String msg) {
        this.msg = msg;
    }

    public ErrorDto(String msg, String code, Object rejectedValue) {
        this.msg = msg;
        this.code = code;
        this.rejectedValue = rejectedValue;
    }

    public ErrorDto(String msg, String stackTrace) {
        this.msg = msg;
        this.stackTrace = stackTrace;
    }
}
