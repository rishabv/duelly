package com.duelly.services.Challenge;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.requests.CreateChallengeRequest;
import com.duelly.entities.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;


public interface ChallengeService {
    List<CategoryDto> getAllCategorylist();

    String createChallenge(CreateChallengeRequest body, User user);
}
