package com.duelly.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDto {
    private Long id;
    private String text;
    private int rating;
    private int helpfulScore;
    private LocalDateTime createdAt;
    private String userName;
    private String userImage;
    private Long userId;
    private Long challengeId;
}
