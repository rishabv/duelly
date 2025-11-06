package com.duelly.dtos.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
public class CreateChallengeRequest implements ChallengeRequest {
    @NotNull(message = "Challenge Name is required")
    private String challengeName;
    @NotNull(message = "Thumbnail image is required")
    private String thumbnailImageUrl;
    @NotNull(message = "Video is required")
    private String videoUrl;
    @NotEmpty(message = "Valid from is required")
    private String validFrom;
    @NotEmpty(message = "Valid to is required")
    private String validTo;
//    @NotEmpty(message = "Company is required")
    @Nullable
    private String companyId;
    private String category;
    @NotNull(message = "Challenge requirement is required")
    private String chellengeRequirement;
    @NotNull(message = "Terms and conditions is required")
    private String termConditions;
    @NotEmpty(message = "Challenge Type is required")
    private String challengeType;
    @NotNull(message = "isPrice is required")
    private boolean isPrice;
    private String priceName;
    private String priceImage;
    private boolean isJudgePrice;
    private String judgePriceImage;
    private String judgePrizeName;
    private String prizeVideo;
    private String prizeDetail;
    private String judgePrizeVideo;
    private String judgePrizeLink;
}
