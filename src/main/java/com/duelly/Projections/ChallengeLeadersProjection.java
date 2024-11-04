package com.duelly.Projections;

import com.duelly.entities.User;
import org.springframework.beans.factory.annotation.Value;

public interface ChallengeLeadersProjection {
    String getTitle();
    String getThumbnailImageUrl();

    Integer getViewsCount();

    UserInfo getUser();

    interface UserInfo {
        String getId();
        String getImage();

        String getFullName();
    }
}
