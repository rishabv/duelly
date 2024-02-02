package com.duelly.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultResponse<T> {
    private List<T> results;
}