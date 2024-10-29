package com.duelly.controllers;

import com.duelly.Projections.ChallengeDetailsProjection;
import com.duelly.constants.RestApiConstant;
import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.requests.CreateChallengeRequest;
import com.duelly.dtos.requests.UpdateChallengePatchRequest;
import com.duelly.dtos.responses.BaseApiResponse;
import com.duelly.dtos.responses.BasePaginationResponse;
import com.duelly.dtos.responses.ChallengeDetailsResponse;
import com.duelly.dtos.responses.ResultResponse;
import com.duelly.entities.Category;
import com.duelly.entities.Challenge;
import com.duelly.entities.Sponsor;
import com.duelly.entities.User;
import com.duelly.services.Challenge.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(RestApiConstant.BASE_URL + RestApiConstant.CHALLENGE)
@Validated
public class ChallengeController {
    @Autowired
    private final ChallengeService challengeService;

    @Operation(summary = "Fetching the list of all the categories.")
    @GetMapping("/category/list")
    public ResponseEntity<BasePaginationResponse<ResultResponse<Category>>> getCategorylist(@ParameterObject @PageableDefault() Pageable pageable) {

        BasePaginationResponse<ResultResponse<Category>> categoryList = challengeService.getAllCategorylist(pageable);
        System.out.println("categoryList "+ categoryList);
        return ResponseEntity.ok().body(categoryList);
    }

    @Operation(summary = "Removing the category on the basis of id")
    @DeleteMapping("/category/{id}")
    public ResponseEntity<BaseApiResponse<String>> deleteCategory(@PathVariable Long id) {
        String msg = challengeService.removeCategory(id);
        if (msg == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new BaseApiResponse<>(msg));
    }

    @PostMapping(RestApiConstant.CREATE_CHALLENGE)
    public ResponseEntity<?> addChallenge(@RequestBody @Valid CreateChallengeRequest body, @AuthenticationPrincipal User user) {
        String message = challengeService.createChallenge(body, user);
        return ResponseEntity.ok(new BaseApiResponse<>(SuccessMessage.CHALLENGE_CREATED));
    }

    @GetMapping("/sponsor/list")
    public ResponseEntity<?> getSponsorList(@ParameterObject @PageableDefault() Pageable pageable){
        BasePaginationResponse<ResultResponse<Sponsor>> sponsorList = challengeService.getAllSponsorlist(pageable);
        return ResponseEntity.ok().body(sponsorList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChallengeDetails(@PathVariable String id){
        Long challengeId = Long.parseLong(id);
        ChallengeDetailsProjection challenge = challengeService.getChallengeDetails(challengeId);
        return ResponseEntity.ok(new BaseApiResponse<>(challenge));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateChallenge(@PathVariable String id, @RequestBody UpdateChallengePatchRequest request) {
        return ResponseEntity.ok(new BaseApiResponse<>(challengeService.updateChallenge(id, request)));
    }

//    @GetMapping("/challenge/list")
//    public ResponseEntity<?> getChallengeList() {
//
//    }
}
