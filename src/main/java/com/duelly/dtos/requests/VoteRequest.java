package com.duelly.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class VoteRequest {
    @Min(value = 0, message = "min")
    @Max(value = 100, message = "max")
    @NotNull(message = "req")
    private Integer skills;
    @Min(value = 0, message = "min")
    @Max(value = 100, message = "max")
    @NotNull(message = "req")
    private Integer presentation;
    @Min(value = 0, message = "min")
    @Max(value = 100, message = "max")
    @NotNull(message = "req")
    private Integer difficulty;
    @Min(value = 0, message = "min")
    @Max(value = 100, message = "max")
    @NotNull(message = "req")
    private Integer technique;
    @NotEmpty(message = "Challenge is required.")
    private String challengeId;

    @NotEmpty(message = "Participant is required.")
    private String participantId;
}
