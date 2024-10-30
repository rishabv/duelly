package com.duelly.Projections;

import com.duelly.entities.User;
import org.springframework.beans.factory.annotation.Value;

public interface ChallengeDetailsProjection {
    Long getId();
    String getChallengeName();
    String getThumbnailImageUrlName();

    String getVideoUrl();
    String getVideoUrlName();

    String getValidFrom();
    String getValidTo();
    String getisPrice();
    String getChallengeRequirement();
    String getChallengeType();

    UserInfo getCreatedBy();

    SponsorInfo getCompany();

    CategoryInfo getCategory();

    boolean getIsActive();

    @Value("#{target.createdBy.id}")
    String getCreatedById();

    interface SponsorInfo {
        String getCompanyName();
    }

    interface CategoryInfo {
        String getCategory();
        String getImage();
    }

    interface UserInfo {
        Long getId();
        String getUserName();
        String getFullName();
        String getPhone();
    }
}
