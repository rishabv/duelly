package com.duelly.entities;

import com.duelly.enums.ChallengeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "challenges")
public class Challenge extends BaseEntity {

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "created_by")
    private User createdBy;
    private String challengeName = "";
    private String thumbnailImageUrlName = "";
    private String videoUrl = "";
    private String videoUrlName = "";
    private String validFrom;
    private String validTo;
    private boolean isPrice = false;
    @Enumerated(EnumType.ORDINAL)
    private ChallengeType challengeType;
    @ManyToOne
    private User user;
    private String challengeRequirement;
    private String termConditions;
    private String priceName;
    private String priceImage;
    private String companyName;
    private Long companyId;
    private int totalJudge = 0;
    private String judgePriceImage;
    private String judgePrizeName;
    private String prizeDetail;
    private String prizeVideo;
    private String judgePrizeVideo;
    private String judgePrizeLink;
    private String judgeId;
    private int totalScore;
    private boolean isLive = false;
    private boolean isActive = false;
    private boolean isWon = false;
    private boolean isJudgePrice = false;
    private boolean isExpired = false;
    private boolean isFlag = false;
}
