package com.duelly.Projections;

import com.duelly.util.Utils;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.Date;

public interface MyChallengesProjection {

    Long getId();
    String getChallengeName();
    String getThumbnailImageUrlName();
    String getValidFrom();
    String getValidTo();

    default boolean getIsExpired() {
        Date date = Utils.getDateFromIsoString(getValidTo());
        return Utils.isAfter(LocalDate.now(), date);
    }

    String getIsFlag();

    @Value("#{target.createdBy.id}")
    String getCreatedById();

}
