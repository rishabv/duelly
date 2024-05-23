package com.duelly.controllers;

import com.duelly.constants.RestApiConstant;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.responses.BaseApiResponse;
import com.duelly.services.Challenge.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(RestApiConstant.BASE_URL + RestApiConstant.CHALLENGE)
public class ChallengeController {
    private final ChallengeService challengeService;

    @Operation(summary = "Fetching the list of all the categories.")
    @GetMapping("/category/list")
    public ResponseEntity<BaseApiResponse<List<CategoryDto>>> getCategorylist() {
        List<CategoryDto> categoryList = challengeService.getAllCategorylist();
        if(categoryList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new BaseApiResponse<>(categoryList     , "List fetched sucessfully."));
    }
}
