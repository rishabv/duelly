package com.duelly.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
//@
public class CreateChallengeRequest implements ChallengeRequest {
    @NotNull(message = "Challenge Name is required")
    private final String challengeName;
    @NotNull(message = "Thumbnail image is required")
    private final String thumbnailImageUrl;
    @NotNull(message = "Video is required")
    private final String videoUrl;
    @NotEmpty(message = "Valid from is required")
    private final String validFrom;
    @NotEmpty(message = "Valid to is required")
    private final String validTo;
    @NotEmpty(message = "Category is required")
    private final String companyId;
    private final String category;
    @NotNull(message = "Challenge requirement is required")
    private final String chellengeRequirement;
    @NotNull(message = "Terms and conditions is required")
    private final String termConditions;
    @NotEmpty(message = "Challenge Type is required")
    private final String challengeType;
    @NotNull(message = "isPrice is required")
    private final boolean isPrice;
    private final String priceName;
    private final String priceImage;
    private final boolean isJudgePrice;
    private final String judgePriceImage;
    private final String judgePrizeName;
    private final String prizeVideo;
    private final String prizeDetail;
    private final String judgePrizeVideo;
    private final String judgePrizeLink;
}
