package com.duelly.controllers;

import com.duelly.constants.RestApiConstant;
import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.requests.CreateChallengeRequest;
import com.duelly.dtos.responses.BaseApiResponse;
import com.duelly.dtos.responses.BasePaginationResponse;
import com.duelly.dtos.responses.ResultResponse;
import com.duelly.entities.Category;
import com.duelly.entities.Challenge;
import com.duelly.entities.User;
import com.duelly.services.Challenge.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
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
    private final ChallengeService challengeService;

    @Operation(summary = "Fetching the list of all the categories.")
    @GetMapping("/category/list")
    public ResponseEntity<BasePaginationResponse> getCategorylist(@ParameterObject @PageableDefault(page=0, size = 10) Pageable pageable) {

        BasePaginationResponse<ResultResponse<Category>> categoryList = challengeService.getAllCategorylist(pageable);

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
}
