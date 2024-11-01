package com.duelly.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipateRequest {
    @NotEmpty(message = "title is required")
    private String title;
    @NotEmpty(message = "Description is required")
    private String videoDesc;
    @NotEmpty(message = "video is required")
    private String videoUrl;
    @NotEmpty(message = "thumbnail is required")
    private String thumbnailImageUrl;
    @NotEmpty(message = "Challenge Id is required")
    private String challengeId;
    @NotEmpty(message = "Company Id is required")
    private String companyId;
}
