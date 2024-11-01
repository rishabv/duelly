package com.duelly.services.Challenge;
import com.duelly.Projections.ChallengeDetailsProjection;
import com.duelly.Projections.MyChallengesProjection;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.requests.CreateChallengeRequest;
import com.duelly.dtos.requests.ParticipateRequest;
import com.duelly.dtos.requests.UpdateChallengePatchRequest;
import com.duelly.dtos.responses.BasePaginationResponse;
import com.duelly.dtos.responses.ChallengeDetailsResponse;
import com.duelly.dtos.responses.ResultResponse;
import com.duelly.entities.Category;
import com.duelly.entities.Challenge;
import com.duelly.entities.Sponsor;
import com.duelly.entities.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ChallengeService {
    BasePaginationResponse<ResultResponse<Category>> getAllCategorylist(Pageable pageable);

    String createChallenge(CreateChallengeRequest body, User user);

    String removeCategory(Long id);
    BasePaginationResponse<ResultResponse<Sponsor>> getAllSponsorlist(Pageable pageable);

    ChallengeDetailsProjection getChallengeDetails(Long id);

    String updateChallenge(String id, UpdateChallengePatchRequest request, User user);
    BasePaginationResponse<ResultResponse<MyChallengesProjection>> getMyChallenges(Pageable pageable, User user);

    String participateChallenge(ParticipateRequest request, User user);
}
