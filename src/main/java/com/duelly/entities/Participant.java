package com.duelly.entities;

import com.duelly.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "participant")
@RequiredArgsConstructor
public class Participant extends BaseEntity {
    private String title;
    private String videoDesc;
    private String videoUrl;
    private String thumbnailImageUrl;
    private boolean isWon = false;
    private int viewsCount;
    private String viewUserId;
    private int totalScore;
    private int totalJudge;
    private int challenge_rank;
    @ManyToOne
    @JoinColumn(name="sponsor_id", referencedColumnName = "id")
    private Sponsor sponsor;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private boolean isDeleted = false;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "challenge_id", referencedColumnName = "id")
    private Challenge challenge;
}
