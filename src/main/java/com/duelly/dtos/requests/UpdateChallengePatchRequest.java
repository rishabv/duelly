package com.duelly.dtos.requests;


import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class UpdateChallengePatchRequest implements ChallengeRequest {
    private Optional<String> challengeName = Optional.empty();
    private Optional<String> thumbnailImageUrlName = Optional.empty();
    private Optional<String> videoUrl = Optional.empty();
    private Optional<String> videoUrlName = Optional.empty();
    private Optional<String> category = Optional.empty();
    private Optional<String> validFrom = Optional.empty();
    private Optional<String> validTo = Optional.empty();
    private Optional<String> challengeRequirement = Optional.empty();
    private Optional<String> termConditions = Optional.empty();
    private Optional<Boolean> isPrice = Optional.empty();
    private Optional<String> priceName = Optional.empty();
    private Optional<String> priceImage = Optional.empty();
    private Optional<String> companyId = Optional.empty();
    private Optional<Boolean> isActive = Optional.empty();
}
