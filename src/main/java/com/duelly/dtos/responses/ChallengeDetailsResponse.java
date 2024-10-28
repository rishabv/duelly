package com.duelly.dtos.responses;

import com.duelly.entities.Category;
import com.duelly.entities.User;
import com.duelly.enums.ChallengeType;
import com.duelly.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChallengeDetailsResponse {
    private String challengeName;
    private User user;
    private Category category;
     private String thumbnailImageUrlName;
    private String viedoUrl;
    private String viedoUrlName;
    private String validFrom;
    private String validTo;
    private String chellengeRequirement;
    private String termConditions;
    @JsonProperty("isPrice")
     private boolean isPrice = false;
    private String priceName = null;
    private String priceImage = null;
    private String priceImageName;
    private int totalParticipant = 0;
    private int viewsCount = 0;
    private ChallengeType challengeType;
     @JsonProperty("isActive")
     private boolean isActive;
     @JsonProperty("isLive")
     private boolean isLive;
     @JsonProperty("isExpired")
    private boolean isExpired;
     private int totalJudge = 0;
    @JsonProperty("isWon")
    private boolean isWon = false;
    @JsonProperty("isJudgePrice")
    private boolean isJudgePrice;
    private String judgePriceImage;
     private String judgePrizeName;
     private String prizeDetail;
     private String prizeVideo;
     private String judgePrizeVideo;
     private String judgePrizeLink;
     private String judgeId;
     private int totalScore;

     public static class CreatedBy {
         private String fullName;
         private String userName;
         private String email;
         private UserRole role;
         private String country;
         private String state;
         private String city;
         private String phone;
     }
}
